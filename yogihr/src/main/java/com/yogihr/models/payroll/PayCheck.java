package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="pay_check")
public class PayCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emp_no")
    private int employeeId;

    @Column(name = "hrs_wrk")
    private double hoursWorked;

    @Column(name = "hrs_pto")
    private double ptoHours;

    @Column(name = "gross_wage")
    private double grossWages;

    @Column(name = "fed_tax")
    private double fedTaxAmt;

    @Column(name = "med_tax")
    private double medTaxAmt;

    @Column(name = "soc_tax")
    private double socTaxAmt;

    @Column(name = "state_tax")
    private double state_tax;

    @Column(name = "tax_total")
    private double taxTotalAmt;

    @Column(name = "dppo")
    private double dentalAmt;

    @Column(name = "ppo")
    private double insuranceAmt;

    @Column(name = "vision")
    private double visionAmt;

    @Column(name = "deduct_total")
    private double deductionsTotalAmt;

    @Column(name = "net_wage")
    private double netWages;

    @Column(name = "chk_date")
    private LocalDate checkDate;

    @Column(name = "year")
    private int year;

    public PayCheck() {
    }

    public PayCheck(int employeeId, double hoursWorked, double ptoHours, double grossWages, double fedTaxAmt, double medTaxAmt, double socTaxAmt, double taxTotalAmt, double dentalAmt, double insuranceAmt, double visionAmt, double deductionsTotalAmt, double netWages, LocalDate checkDate, int year) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.ptoHours = ptoHours;
        this.grossWages = grossWages;
        this.fedTaxAmt = fedTaxAmt;
        this.medTaxAmt = medTaxAmt;
        this.socTaxAmt = socTaxAmt;
        this.taxTotalAmt = taxTotalAmt;
        this.dentalAmt = dentalAmt;
        this.insuranceAmt = insuranceAmt;
        this.visionAmt = visionAmt;
        this.deductionsTotalAmt = deductionsTotalAmt;
        this.netWages = netWages;
        this.checkDate = checkDate;
        this.year = year;
    }

    public PayCheck(int employeeId, double hoursWorked, double ptoHours, double grossWages, double fedTaxAmt, double medTaxAmt, double socTaxAmt, double state_tax, double taxTotalAmt, double dentalAmt, double insuranceAmt, double visionAmt, double deductionsTotalAmt, double netWages, LocalDate checkDate, int year) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.ptoHours = ptoHours;
        this.grossWages = grossWages;
        this.fedTaxAmt = fedTaxAmt;
        this.medTaxAmt = medTaxAmt;
        this.socTaxAmt = socTaxAmt;
        this.state_tax = state_tax;
        this.taxTotalAmt = taxTotalAmt;
        this.dentalAmt = dentalAmt;
        this.insuranceAmt = insuranceAmt;
        this.visionAmt = visionAmt;
        this.deductionsTotalAmt = deductionsTotalAmt;
        this.netWages = netWages;
        this.checkDate = checkDate;
        this.year = year;
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

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getPtoHours() {
        return ptoHours;
    }

    public void setPtoHours(double ptoHours) {
        this.ptoHours = ptoHours;
    }

    public double getGrossWages() {
        return grossWages;
    }

    public void setGrossWages(double grossWages) {
        this.grossWages = grossWages;
    }

    public double getFedTaxAmt() {
        return fedTaxAmt;
    }

    public void setFedTaxAmt(double fedTaxAmt) {
        this.fedTaxAmt = fedTaxAmt;
    }

    public double getMedTaxAmt() {
        return medTaxAmt;
    }

    public void setMedTaxAmt(double medTaxAmt) {
        this.medTaxAmt = medTaxAmt;
    }

    public double getSocTaxAmt() {
        return socTaxAmt;
    }

    public void setSocTaxAmt(double socTaxAmt) {
        this.socTaxAmt = socTaxAmt;
    }

    public double getTaxTotalAmt() {
        return taxTotalAmt;
    }

    public void setTaxTotalAmt(double taxTotalAmt) {
        this.taxTotalAmt = taxTotalAmt;
    }

    public double getDentalAmt() {
        return dentalAmt;
    }

    public void setDentalAmt(double dentalAmt) {
        this.dentalAmt = dentalAmt;
    }

    public double getInsuranceAmt() {
        return insuranceAmt;
    }

    public void setInsuranceAmt(double insuranceAmt) {
        this.insuranceAmt = insuranceAmt;
    }

    public double getVisionAmt() {
        return visionAmt;
    }

    public void setVisionAmt(double visionAmt) {
        this.visionAmt = visionAmt;
    }

    public double getDeductionsTotalAmt() {
        return deductionsTotalAmt;
    }

    public void setDeductionsTotalAmt(double deductionsTotalAmt) {
        this.deductionsTotalAmt = deductionsTotalAmt;
    }

    public double getNetWages() {
        return netWages;
    }

    public void setNetWages(double netWages) {
        this.netWages = netWages;
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
}
