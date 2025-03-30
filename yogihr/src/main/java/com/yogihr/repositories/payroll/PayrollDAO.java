package com.yogihr.repositories.payroll;

import com.yogihr.models.payroll.Deductions;
import com.yogihr.models.payroll.PayCheck;
import com.yogihr.models.payroll.PayPeriod;
import com.yogihr.models.payroll.TimeSheet;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


public interface PayrollDAO {
    TimeSheet findTimeSheetById(int id);

    TimeSheet save(TimeSheet timeSheet);

    PayPeriod findPayPeriodById(int id);

    PayPeriod findPayPeriodByCurrentDate();
    void save(PayPeriod payPeriod);

    Deductions findDeductionsByEmpId(int id);

    void save (Deductions deductions);

    void save (PayCheck payCheck);

    List<PayPeriod> findPayPeriodsByYear(int year);

    List<Integer> getPayPeriodYears();

    List<TimeSheet> findTimeSheetAndWorkhoursByEmployeeIdAndPayPeriod(int id, int payPeriodId);

    List<TimeSheet> findAllUnapprovedTimeSheets(int payPeriodId);

    List<TimeSheet> findAllApprovedTimeSheets(int payPeriodId);

    PayCheck findPayCheckById(int id);

    List<PayCheck> findAllPayChecksByEmpID(int id);

    List<PayCheck> findAllPayChecksByEmpIDAndYear(int id, int year);


//    List<TimeSheet> findAllApprovedTimeSheetsByPayPeriodId(int payPeriodId);
}
