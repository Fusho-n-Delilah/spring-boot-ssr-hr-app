package com.yogihr.dtos;

import com.yogihr.models.payroll.TimeSheet;
import com.yogihr.models.payroll.WorkHours;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public class WebTimeSheet {

    private int employeeId;

    private int payPeriod;

    @Valid
    private List<WebWorkHours> workHours;

    public WebTimeSheet() {
    }

    public WebTimeSheet(int employeeId, int payPeriod, List<WebWorkHours> workHours) {
        this.employeeId = employeeId;
        this.payPeriod = payPeriod;
        this.workHours = workHours;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(int payPeriod) {
        this.payPeriod = payPeriod;
    }

    public List<WebWorkHours> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WebWorkHours> workHours) {
        this.workHours = workHours;
    }
}
