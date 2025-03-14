package com.yogihr.models;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private int id;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @OneToOne(mappedBy ="employee",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;


    public Employee() {
    }

    public Employee(Date birthDate, String firstName, String lastName, String gender, Date hireDate) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
