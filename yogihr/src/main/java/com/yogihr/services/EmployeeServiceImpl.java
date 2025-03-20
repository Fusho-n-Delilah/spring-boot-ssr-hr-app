package com.yogihr.services;

import com.yogihr.models.employee.Employee;
import com.yogihr.repositories.employee.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee findById(int id) {
       return employeeDao.findById(id);
    }

    @Override
    public Employee findEmployeeWithDetails(int id) {
        return employeeDao.findEmployeeJoinFetchAll(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllOrderByLastNameAsc();
    }

    @Override
    public List<Employee> findByKeywordContainingIgnoreCase(String keyword) {
        return employeeDao.findByKeywordContainingIgnoreCase(keyword);
    }

    @Override
    public Employee findEmployeeWithContactInfo(Employee employee) {
        int id = employee.getId();

        employee = employeeDao.findEmployeeJoinFetchContactById(id);

        return employee;
    }

    @Override
    public Employee findEmployeeWithDepartmentInfo(Employee employee) {
        int id = employee.getId();

        employee = employeeDao.findEmployeeJoinFetchDepartmentById(id);

        return employee;
    }

    @Override
    public Employee findEmployeeWithTitleAndDepartmentInfo(Employee employee) {
        int id = employee.getId();

        employee = employeeDao.findEmployeeJoinFetchTitleAndDepartmentById(id);

        return employee;
    }

    @Override
    public Employee findEmployeeWithSalaryInfo(Employee employee) {
        int id = employee.getId();

        employee = employeeDao.findEmployeeJoinFetchSalaryInfoById(id);

        return employee;
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }
}
