package com.yogihr.repositories;

import com.yogihr.models.Employee;

public interface EmployeeDao {
    Employee findById(int id);
}
