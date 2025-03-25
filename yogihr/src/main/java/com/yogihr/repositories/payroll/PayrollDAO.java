package com.yogihr.repositories.payroll;

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

    List<TimeSheet> findTimeSheetAndWorkhoursByEmployeeIdAndPayPeriod(int id, int payPeriodId);

    List<TimeSheet> findAllUnapprovedTimeSheets(int payPeriodId);


//    List<TimeSheet> findAllApprovedTimeSheetsByPayPeriodId(int payPeriodId);
}
