package com.yogihr.repositories;

import com.yogihr.models.Employee;

public interface EmployeeDao {
    Employee findById(int id);

    Employee findEmployeeJoinFetchContactById(int id);

    void save(Employee employee);
}
