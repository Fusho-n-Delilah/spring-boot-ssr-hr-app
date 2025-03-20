package com.yogihr.services;

import com.yogihr.models.employee.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findById(int id);

    Employee findEmployeeWithDetails(int id);

    List<Employee> findAllEmployees();

    List<Employee> findByKeywordContainingIgnoreCase(String keyword);

    Employee findEmployeeWithContactInfo(Employee employee);

    Employee findEmployeeWithDepartmentInfo(Employee employee);

    Employee findEmployeeWithTitleAndDepartmentInfo(Employee employee);

    Employee findEmployeeWithSalaryInfo(Employee employee);

    void save(Employee employee);
}
