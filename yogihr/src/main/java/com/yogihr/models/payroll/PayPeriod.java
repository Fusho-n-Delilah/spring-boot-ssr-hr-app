package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pay_periods")
public class PayPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "status")
    private int status;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "chk_date")
    private LocalDate checkDate;

    @Column(name = "year")
    private int year;

    public PayPeriod() {
    }

    public PayPeriod(String schedule, int status, LocalDate fromDate, LocalDate toDate, LocalDate checkDate, int year) {
        this.schedule = schedule;
        this.status = status;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.checkDate = checkDate;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "PayPeriod{" +
                "id=" + id +
                ", schedule='" + schedule + '\'' +
                ", status=" + status +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", checkDate=" + checkDate +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayPeriod payPeriod = (PayPeriod) o;
        return id == payPeriod.id && status == payPeriod.status && year == payPeriod.year && Objects.equals(schedule, payPeriod.schedule) && Objects.equals(fromDate, payPeriod.fromDate) && Objects.equals(toDate, payPeriod.toDate) && Objects.equals(checkDate, payPeriod.checkDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schedule, status, fromDate, toDate, checkDate, year);
    }
}
