package com.yogihr.repositories.employee;

import com.yogihr.models.employee.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAllOrderByLastNameAsc();
    List<Employee> findByKeywordContainingIgnoreCase(String keyword);

    Employee findById(int id);

    Employee findEmployeeJoinFetchAll(int id);

    Employee findEmployeeJoinFetchContactById(int id);

    Employee findEmployeeJoinFetchDepartmentById(int id);

    Employee findEmployeeJoinFetchTitleAndDepartmentById(int id);

    Employee findEmployeeJoinFetchSalaryInfoById(int id);

    void save(Employee employee);
}
