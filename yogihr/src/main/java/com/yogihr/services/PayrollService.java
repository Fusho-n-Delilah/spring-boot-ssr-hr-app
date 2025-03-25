package com.yogihr.services;

import com.yogihr.dtos.WebTimeSheet;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.payroll.PayPeriod;
import com.yogihr.models.payroll.TimeSheet;

import java.util.List;

public interface PayrollService {
    TimeSheet findTimeSheetById(int id);

    TimeSheet save(TimeSheet timeSheet);

    TimeSheet save(WebTimeSheet timeSheet);

    void saveApprovedTimeSheet(Employee approver, TimeSheet timeSheet);

    PayPeriod findPayPeriodById(int id);

    PayPeriod findPayPeriodByCurrentDate();

    List<TimeSheet> findTimeSheetByEmployeeIdAndPayPeriod(int id, int payPeriodId);

    List<TimeSheet> findAllUnapprovedTimeSheets();
}
