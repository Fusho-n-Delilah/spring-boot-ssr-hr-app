package com.yogihr.dtos;

import com.yogihr.models.payroll.WorkHours;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class WebWorkHours {

    private int employeeId;

    private LocalDate date;

    @Min(value = 0, message = "Value must be greater than or equal to 0")
    @Max(value = 20, message = "Value must be less than or equal to 20")
    private double hours;

    private int payPeriod;

    public WebWorkHours() {
    }

    public WebWorkHours(int employeeId, LocalDate date, int payPeriod) {
        this.employeeId = employeeId;
        this.date = date;
        this.hours = 0.0;
        this.payPeriod = payPeriod;
    }

    public WebWorkHours(int employeeId, LocalDate date, double hours, int payPeriod) {
        this(employeeId, date, payPeriod);
        this.hours = hours;
    }

    public WebWorkHours(WorkHours workHours) {
        this.employeeId = workHours.getEmployeeId();
        this.date = workHours.getDate();
        this.hours = workHours.getHours();
        this.payPeriod = workHours.getPayPeriod();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public int getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(int payPeriod) {
        this.payPeriod = payPeriod;
    }
}
