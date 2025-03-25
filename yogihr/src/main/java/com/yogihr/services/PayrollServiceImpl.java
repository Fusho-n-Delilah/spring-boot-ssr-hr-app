package com.yogihr.services;

import com.yogihr.dtos.WebTimeSheet;
import com.yogihr.dtos.WebWorkHours;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.payroll.PayPeriod;
import com.yogihr.models.payroll.TimeSheet;
import com.yogihr.models.payroll.WorkHours;
import com.yogihr.repositories.payroll.PayrollDAO;
import jakarta.transaction.Transactional;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService{

    private PayrollDAO payrollDAO;

    public PayrollServiceImpl(PayrollDAO payrollDAO){
        this.payrollDAO = payrollDAO;
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
}
