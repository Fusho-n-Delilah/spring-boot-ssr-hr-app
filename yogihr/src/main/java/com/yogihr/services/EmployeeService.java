package com.yogihr.services;

import com.yogihr.models.Employee;

public interface EmployeeService {
    Employee findById(int id);

    Employee findEmployeeWithContactInfo(Employee employee);

    void save(Employee employee);
}
