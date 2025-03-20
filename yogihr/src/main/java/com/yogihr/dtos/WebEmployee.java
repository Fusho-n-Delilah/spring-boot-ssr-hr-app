package com.yogihr.dtos;

import com.yogihr.models.employee.Contact;
import com.yogihr.models.employee.Department;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.employee.Title;
import com.yogihr.models.payroll.Salary;
import com.yogihr.models.payroll.SalaryInfo;
import com.yogihr.models.security.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class WebEmployee {

    @NotNull
    private int id;

    @NotNull(message = "is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 14, message = "is too long")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 16, message = "is too long")
    private String lastName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 1, message = "is too long")
    private String gender;

    @NotNull(message = "is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

//    private Salary salary

    @NotNull(message = "is required")
    private int salary;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String employeeType;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String payType;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String payFreq;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String dolStatus;
    @NotNull(message = "is required")
    private double fedTax;
    @NotNull(message = "is required")
    private double medTax;
    @NotNull(message = "is required")
    private double socSecTax;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String title;


    private String department;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 50, message = "is too long")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String workEmail;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 50, message = "is too long")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String personalEmail;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 15, message = "is too long, please use a format with less characters")
    private String phoneNumber;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 50, message = "is too long")
    private String streetAddress;

    @Size(max = 20, message = "is too long")
    private String apt;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 30, message = "is too long. Please abbreviate the city name.")
    private String city;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 11, message = "is too long of a name")
    private String state;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 10, message = "is too long. please use a standard US postal code.")
    @Pattern(regexp = "[0-9]{5}(['-']?[0-9]{4})?$")
    private String postalCode;

    public WebEmployee(){

    }

    public WebEmployee(Employee employee) {
        Contact employeeContact = employee.getContact();
        Department employeeDepartment = employee.getDepartments().get(employee.getDepartments().size()-1);
        Title employeeTitle = employee.getTitles().get(employee.getTitles().size()-1);
        Salary employeeSalary = employee.getSalaries().get(employee.getSalaries().size()-1);
        SalaryInfo employeeSalaryInfo = employeeSalary.getSalaryInfo();

        this.id = employee.getId();
        this.birthDate = employee.getBirthDate();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.gender = employee.getGender();
        this.hireDate = employee.getHireDate();
        this.salary = employeeSalary.getSalary();
        this.employeeType = employeeSalaryInfo.getEmployeeType();
        this.payType = employeeSalaryInfo.getPayType();
        this.payFreq = employeeSalaryInfo.getPayFreq();
        this.dolStatus = employeeSalaryInfo.getDolStatus();
        this.fedTax = employeeSalaryInfo.getFedTax();
        this.medTax = employeeSalaryInfo.getMedTax();
        this.socSecTax = employeeSalaryInfo.getSocSecTax();
        this.title = employeeTitle.getTitle();
        this.department = employeeDepartment.getDeptName();
        this.workEmail = employeeContact.getWorkEmail();
        this.personalEmail = employeeContact.getPersonalEmail();
        this.phoneNumber = employeeContact.getPhoneNumber();
        this.streetAddress = employeeContact.getStreetAddress();
        this.apt = employeeContact.getApt();
        this.city = employeeContact.getCity();
        this.state = employeeContact.getState();
        this.postalCode = employeeContact.getPostalCode();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "WebEmployee{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", employeeType='" + employeeType + '\'' +
                ", payType='" + payType + '\'' +
                ", payFreq='" + payFreq + '\'' +
                ", dolStatus='" + dolStatus + '\'' +
                ", fedTax=" + fedTax +
                ", medTax=" + medTax +
                ", socSecTax=" + socSecTax +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", workEmail='" + workEmail + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", apt='" + apt + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
