package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pto_requests")
public class PTORequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emp_no")
    private int employeeId;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "total_hours")
    private Double totalHours;

    @Column(name = "approved")
    private int approved;

    public PTORequest() {
    }

    public PTORequest(int employeeId, LocalDate fromDate, LocalDate toDate, Double totalHours, int approved) {
        this.employeeId = employeeId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalHours = totalHours;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int isApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return  "From: " + fromDate +
                "    To: " + toDate +
                "    Total hours: " + totalHours +
                "    Approved: " + ((approved == 1) ? "Yes" : "No");
    }
}
