package com.yogihr.services;

import com.yogihr.dtos.WebTimeSheet;
import com.yogihr.dtos.WebWorkHours;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.payroll.*;
import com.yogihr.repositories.employee.EmployeeDao;
import com.yogihr.repositories.payroll.PTORequestDAO;
import com.yogihr.repositories.payroll.PayrollDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PayrollServiceImpl implements PayrollService{

    private PayrollDAO payrollDAO;
    private EmployeeDao employeeDao;

    private PTORequestDAO ptoRequestDAO;

    @Autowired
    public PayrollServiceImpl(PayrollDAO payrollDAO,
                              EmployeeDao employeeDao,
                              PTORequestDAO ptoRequestDAO){
        this.payrollDAO = payrollDAO;
        this.employeeDao = employeeDao;
        this.ptoRequestDAO = ptoRequestDAO;
    }

    @Override
    public TimeSheet findTimeSheetById(int id) {
        return payrollDAO.findTimeSheetById(id);
    }

    @Override
    @Transactional
    public TimeSheet save(TimeSheet timeSheet) {
        return payrollDAO.save(timeSheet);
    }

    @Override
    @Transactional
    public TimeSheet save(WebTimeSheet timeSheet) {
        //get pay period
        PayPeriod payPeriod = this.findPayPeriodByCurrentDate();

        //get work hours
        List<WebWorkHours> workHours = timeSheet.getWorkHours();

        //check if existing time
        List<TimeSheet> existingTimeSheets = this.findTimeSheetByEmployeeIdAndPayPeriod(timeSheet.getEmployeeId(), payPeriod.getId());

        TimeSheet dbTimeSheet;
        if(existingTimeSheets.isEmpty()){
            //convert DTOs into the DAO versions
            dbTimeSheet = new TimeSheet(timeSheet.getEmployeeId(), payPeriod.getId(), LocalDate.now());
            System.out.println(dbTimeSheet);

            dbTimeSheet = this.save(dbTimeSheet);


            for(WebWorkHours wh : workHours){
                WorkHours dbWorkHours = new WorkHours(wh.getEmployeeId(), wh.getDate(), wh.getHours(), wh.getPayPeriod(), dbTimeSheet.getId());

                dbTimeSheet.add(dbWorkHours);
            }
        } else {
            dbTimeSheet = existingTimeSheets.get(0);
            List<WorkHours> dbWorkHours = dbTimeSheet.getWorkHours();

            for(int i = 0; i < dbWorkHours.size(); i++){
                if(dbWorkHours.get(i).getDate().equals(workHours.get(i).getDate())){
                    dbWorkHours.get(i).setHours(workHours.get(i).getHours());
                }
            }
        }

        //save/merge the timesheet to the database
        this.save(dbTimeSheet);

        return payrollDAO.save(dbTimeSheet);
    }

    @Override
    public void saveApprovedTimeSheet(Employee approver, TimeSheet timeSheet) {

        // set approver details
        timeSheet.setApproverId(approver.getId());
        timeSheet.setApprovedDate(LocalDate.now());

        payrollDAO.save(timeSheet);
    }

    @Override
    public PayPeriod findPayPeriodById(int id) {
        return payrollDAO.findPayPeriodById(id);
    }

    @Override
    public PayPeriod findPayPeriodByCurrentDate() {
        return payrollDAO.findPayPeriodByCurrentDate();
    }

    @Override
    public List<PayPeriod> findPayPeriodsByYear(int year) {
        return payrollDAO.findPayPeriodsByYear(year);
    }

    @Override
    public List<Integer> getPayPeriodYears() {
        return payrollDAO.getPayPeriodYears();
    }

    @Override
    public List<TimeSheet> findTimeSheetByEmployeeIdAndPayPeriod(int id, int payPeriodId) {
        return payrollDAO.findTimeSheetAndWorkhoursByEmployeeIdAndPayPeriod(id, payPeriodId);
    }

    @Override
    public List<TimeSheet> findAllUnapprovedTimeSheets() {
        // get the current pay period
        PayPeriod payPeriod = this.findPayPeriodByCurrentDate();

        // get all unnapproved timesheet for the payperiod
        List<TimeSheet> unnapprovedTimesheets = payrollDAO.findAllUnapprovedTimeSheets(payPeriod.getId());

        return unnapprovedTimesheets;
    }

    @Override
    public List<TimeSheet> findAllApprovedTimeSheets(int payPeriodId) {
        return  payrollDAO.findAllApprovedTimeSheets(payPeriodId);
    }

    @Override
    public Boolean processPayroll(PayPeriod payPeriodForProcessing) {
        List<TimeSheet> approvedTimeSheets = this.findAllApprovedTimeSheets(payPeriodForProcessing.getId());

        System.out.println(approvedTimeSheets);

        if(!approvedTimeSheets.isEmpty()){
            approvedTimeSheets.forEach(timeSheet -> {

                //get the employee information
                Employee employee = employeeDao.findEmployeeJoinFetchSalaryInfoById(timeSheet.getEmployeeId());

                //get the salary information for the employee
                Salary employeeSalary = employee.getSalaries()
                        .get(employee.getSalaries().size()-1);
                SalaryInfo employeeSalaryInfo = employeeSalary.getSalaryInfo();

                //get approved pto request hours
                LocalDate fromDate = payPeriodForProcessing.getFromDate();
                LocalDate toDate = payPeriodForProcessing.getToDate();

                List<PTORequest> ptoRequests = ptoRequestDAO.findAllApprovedByEmpIDAndDateRange(employee.getId(),
                                                                                                fromDate,
                                                                                                toDate);

                //get total PTO hours
                double ptoHours = this.getPTOHours(ptoRequests, fromDate, toDate);

                // get the total hours worked
                double totalWorkHours = timeSheet.getWorkHours().stream()
                                                                .mapToDouble(WorkHours::getHours)
                                                                .sum();

                //create and persist paycheck
                this.createPaycheck(totalWorkHours, ptoHours,
                                    employeeSalary, employeeSalaryInfo,
                                    payPeriodForProcessing);

                //save pay period and set paid flag
                payPeriodForProcessing.setStatus(1);
                payrollDAO.save(payPeriodForProcessing);

            });
        }


        return true;
    }

    @Override
    public Double getPTOHours(List<PTORequest> ptoRequests,
                              LocalDate fromDate, LocalDate toDate) {

        AtomicReference<Double> hoursTotal = new AtomicReference<>((double) 0);
        ptoRequests.forEach(request -> {
            LocalDate start = request.getFromDate();
            LocalDate end = request.getToDate();

            while (!start.isAfter(end)){
                if((start.isAfter(fromDate) && start.isBefore(toDate))
                        || start.isEqual(fromDate)
                        || start.isEqual(toDate)){

                    hoursTotal.updateAndGet(v -> (double) (v + 8.0));
                    start = start.plusDays(1);
                }
            }
        });

        return hoursTotal.get();
    }

    @Override
    public void createPaycheck(double workHours, double ptoHours,
                               Salary salary, SalaryInfo salaryInfo,
                               PayPeriod payPeriod) {

        //get hourly rate
        double employeeHourlyRate = salary.getSalary() / 2080.0;

        //get deductions

        // create a new paycheck
        PayCheck check = new PayCheck();

        // use setters to populate data
        check.setYear(payPeriod.getYear());
        check.setCheckDate(payPeriod.getCheckDate());
        check.setEmployeeId(salaryInfo.getId());
        check.setHoursWorked(workHours);
        check.setPtoHours(ptoHours);

        double grossWages = (workHours * employeeHourlyRate) + (ptoHours * employeeHourlyRate);
        check.setGrossWages(grossWages);

        //taxes
        double federalTaxes = grossWages * salaryInfo.getFedTax();
        check.setFedTaxAmt(federalTaxes);

        double medicareTaxes = grossWages * salaryInfo.getMedTax();
        check.setMedTaxAmt(medicareTaxes);

        double socSecurityTaxes = grossWages * salaryInfo.getSocSecTax();
        check.setSocTaxAmt(socSecurityTaxes);

        double taxTotal = federalTaxes + medicareTaxes + socSecurityTaxes;
        check.setTaxTotalAmt(taxTotal);

        // insurance deductables
        Deductions deductions = payrollDAO.findDeductionsByEmpId(salaryInfo.getId());
        double healthInsuranceAmt = deductions.getHealth();
        double dentalInsuranceAmt = deductions.getDental();
        double visionInsuranceAmt = deductions.getVision();
        check.setInsuranceAmt(healthInsuranceAmt);
        check.setDentalAmt(dentalInsuranceAmt);
        check.setVisionAmt(visionInsuranceAmt);

        double deductionsTotal = healthInsuranceAmt + dentalInsuranceAmt + visionInsuranceAmt;
        check.setDeductionsTotalAmt(deductionsTotal);

        //net wages
        double netWages = grossWages - (taxTotal + deductionsTotal);
        check.setNetWages(netWages);

        //persist check
        payrollDAO.save(check);
    }

    @Override
    public PayCheck findPayCheckById(int id) {
        return payrollDAO.findPayCheckById(id);
    }

    @Override
    public PayCheck getYearToDateTotalsByEmpId(int id, int year) {

        //get list of all paychecks by year
        List<PayCheck> payCheckList = this.findAllPayChecksByEmpIdAndYear(id, year);

        //total the amounts and add to a paycheck object
        PayCheck yearToDate = new PayCheck();

        if(!payCheckList.isEmpty()){
            yearToDate.setYear(year);
            yearToDate.setEmployeeId(id);
            yearToDate.setGrossWages(
                    payCheckList.stream().mapToDouble(PayCheck::getGrossWages).sum()
            );
            yearToDate.setFedTaxAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getFedTaxAmt).sum()
            );
            yearToDate.setMedTaxAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getMedTaxAmt).sum()
            );
            yearToDate.setSocTaxAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getSocTaxAmt).sum()
            );
            yearToDate.setTaxTotalAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getTaxTotalAmt).sum()
            );
            yearToDate.setDentalAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getDentalAmt).sum()
            );
            yearToDate.setInsuranceAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getInsuranceAmt).sum()
            );
            yearToDate.setVisionAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getVisionAmt).sum()
            );
            yearToDate.setDeductionsTotalAmt(
                    payCheckList.stream().mapToDouble(PayCheck::getDeductionsTotalAmt).sum()
            );
            yearToDate.setNetWages(
                    payCheckList.stream().mapToDouble(PayCheck::getNetWages).sum()
            );
        } else {
            yearToDate = new PayCheck(id,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, LocalDate.now(), year);
        }

        return yearToDate;
    }

    @Override
    public List<PayCheck> findAllPayChecksByEmpId(int id) {
        return payrollDAO.findAllPayChecksByEmpID(id);
    }

    @Override
    public List<PayCheck> findAllPayChecksByEmpIdAndYear(int id, int year) {
        return payrollDAO.findAllPayChecksByEmpIDAndYear(id, year);
    }
}
