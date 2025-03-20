package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "salary_info")
public class SalaryInfo {

    @Id
    @Column(name = "emp_no")
    private int id;
    @Column(name = "emp_type")
    private String employeeType;
    @Column(name = "pay_type")
    private String payType;
    @Column(name = "pay_freq")
    private String payFreq;
    @Column(name = "dol_status")
    private String dolStatus;
    @Column(name = "fed_tax")
    private double fedTax;
    @Column(name = "med_tax")
    private double medTax;
    @Column(name = "soc_tax")
    private double socSecTax;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;

    @OneToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.ALL, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "emp_no")
    private List<Salary> salaries;

    public SalaryInfo(){

    }

    public SalaryInfo(int id, String employeeType, String payType, String payFreq, String dolStatus, double fedTax, double medTax, double socSecTax, Date fromDate, Date toDate) {
        this.id = id;
        this.employeeType = employeeType;
        this.payType = payType;
        this.payFreq = payFreq;
        this.dolStatus = dolStatus;
        this.fedTax = fedTax;
        this.medTax = medTax;
        this.socSecTax = socSecTax;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayFreq() {
        return payFreq;
    }

    public void setPayFreq(String payFreq) {
        this.payFreq = payFreq;
    }

    public String getDolStatus() {
        return dolStatus;
    }

    public void setDolStatus(String dolStatus) {
        this.dolStatus = dolStatus;
    }

    public double getFedTax() {
        return fedTax;
    }

    public void setFedTax(double fedTax) {
        this.fedTax = fedTax;
    }

    public double getMedTax() {
        return medTax;
    }

    public void setMedTax(double medTax) {
        this.medTax = medTax;
    }

    public double getSocSecTax() {
        return socSecTax;
    }

    public void setSocSecTax(double socSecTax) {
        this.socSecTax = socSecTax;
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

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "SalaryInfo{" +
                "id=" + id +
                ", employeeType='" + employeeType + '\'' +
                ", payType='" + payType + '\'' +
                ", payFreq='" + payFreq + '\'' +
                ", dolStatus='" + dolStatus + '\'' +
                ", fedTax=" + fedTax +
                ", medTax=" + medTax +
                ", socSecTax=" + socSecTax +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryInfo that = (SalaryInfo) o;
        return id == that.id && Double.compare(fedTax, that.fedTax) == 0 && Double.compare(medTax, that.medTax) == 0 && Double.compare(socSecTax, that.socSecTax) == 0 && Objects.equals(employeeType, that.employeeType) && Objects.equals(payType, that.payType) && Objects.equals(payFreq, that.payFreq) && Objects.equals(dolStatus, that.dolStatus) && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeType, payType, payFreq, dolStatus, fedTax, medTax, socSecTax, fromDate, toDate, salaries);
    }
}
