package com.yogihr.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class WebPTORequest {

    private int employeeNumber;

    @NotNull(message = "is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @NotNull(message = "is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;


    private Double totalHours;


    private boolean approved;

    public WebPTORequest() {
    }

    public WebPTORequest(LocalDate fromDate, LocalDate toDate, Double totalHours, boolean approved) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalHours = totalHours;
        this.approved = approved;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeNumber) {
        this.employeeNumber = employeNumber;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
