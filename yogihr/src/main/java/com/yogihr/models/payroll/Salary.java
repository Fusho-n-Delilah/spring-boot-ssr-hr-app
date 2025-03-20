package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @Column(name = "emp_no")
    private int id;

    @Column(name = "amount")
    private int salary;

    @Id
    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne(fetch = FetchType.EAGER,
                cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "emp_no")
    private SalaryInfo salaryInfo;

    public Salary() {
    }

    public Salary(int id, int salary, Date fromDate, Date toDate) {
        this.id = id;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public SalaryInfo getSalaryInfo() {
        return salaryInfo;
    }

    public void setSalaryInfo(SalaryInfo salaryInfo) {
        this.salaryInfo = salaryInfo;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", salary=" + salary +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
