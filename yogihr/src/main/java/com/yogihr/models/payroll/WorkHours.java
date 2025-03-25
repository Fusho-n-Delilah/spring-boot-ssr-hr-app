package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "work_hours")
public class WorkHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emp_no")
    private int employeeId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hours")
    private double hours;

    @Column(name = "pay_period_id")
    private int payPeriod;

    @Column(name = "time_sheet_id")
    private int timeSheetId;

//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,
//                            CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinColumn(name = "time_sheet_id")
//    private TimeSheet timeSheet;
    public WorkHours() {
    }

    public WorkHours(int employeeId, LocalDate date, double hours, int payPeriod, int timeSheetId) {
        this.employeeId = employeeId;
        this.date = date;
        this.hours = hours;
        this.payPeriod = payPeriod;
        this.timeSheetId = timeSheetId;
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

    public int getTimeSheetId() {
        return timeSheetId;
    }

    public void setTimeSheetId(int timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    @Override
    public String toString() {
        return "WorkHours{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", date=" + date +
                ", hours=" + hours +
                ", payPeriod=" + payPeriod +
                ", timeSheetId=" + timeSheetId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkHours workHours = (WorkHours) o;
        return id == workHours.id && employeeId == workHours.employeeId && Double.compare(hours, workHours.hours) == 0 && payPeriod == workHours.payPeriod && Objects.equals(date, workHours.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, date, hours, payPeriod);
    }
}
