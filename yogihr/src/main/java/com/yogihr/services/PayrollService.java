package com.yogihr.services;

import com.yogihr.dtos.WebTimeSheet;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.payroll.*;

import java.time.LocalDate;
import java.util.List;

public interface PayrollService {
    TimeSheet findTimeSheetById(int id);

    TimeSheet save(TimeSheet timeSheet);

    TimeSheet save(WebTimeSheet timeSheet);

    void saveApprovedTimeSheet(Employee approver, TimeSheet timeSheet);

    PayPeriod findPayPeriodById(int id);

    PayPeriod findPayPeriodByCurrentDate();

    List<PayPeriod> findPayPeriodsByYear(int year);

    List<Integer> getPayPeriodYears();

    List<TimeSheet> findTimeSheetByEmployeeIdAndPayPeriod(int id, int payPeriodId);

    List<TimeSheet> findAllUnapprovedTimeSheets();

    List<TimeSheet> findAllApprovedTimeSheets(int payPeriodId);

    Boolean processPayroll(PayPeriod payPeriodForProcessing);

    Double getPTOHours(List<PTORequest> ptoRequests, LocalDate fromDate, LocalDate toDate);

    void createPaycheck(double workHours, double ptoHours, Salary salary, SalaryInfo salaryInfo, PayPeriod payPeriod);

    PayCheck findPayCheckById(int id);

    PayCheck getYearToDateTotalsByEmpId(int id, int year);

    List<PayCheck> findAllPayChecksByEmpId(int id);

    List<PayCheck> findAllPayChecksByEmpIdAndYear(int id, int year);
}
