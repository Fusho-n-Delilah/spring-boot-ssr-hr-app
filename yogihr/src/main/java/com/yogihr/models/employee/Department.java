package com.yogihr.models.employee;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dept_no")
    private String deptId;

    @Column(name = "dept_name")
    private String deptName;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "dept_emp",
                joinColumns = @JoinColumn(name = "dept_no"),
                inverseJoinColumns = @JoinColumn(name = "emp_no"))
    private List<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "dept_manager",
            joinColumns = @JoinColumn(name = "dept_no"),
            inverseJoinColumns = @JoinColumn(name = "emp_no"))
    private List<Employee> managers;



    public Department(){}

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getManagers() {
        return managers;
    }

    public void setManagers(List<Employee> managers) {
        this.managers = managers;
    }

    public void addEmployee(Employee employee){
        if (employees == null){
            employees = new ArrayList<>();
        }

        employees.add(employee);
    }

    public void addManager(Employee employee){
        if (managers == null){
            managers = new ArrayList<>();
        }

        employees.add(employee);
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(deptId, that.deptId) && Objects.equals(deptName, that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, deptName);
    }
}
