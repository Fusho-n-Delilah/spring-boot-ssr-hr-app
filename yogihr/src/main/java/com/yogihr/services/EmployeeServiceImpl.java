package com.yogihr.services;

import com.yogihr.models.Employee;
import com.yogihr.repositories.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Employee findEmployeeWithContactInfo(Employee employee) {
        int id = employee.getId();

        employee = employeeDao.findEmployeeJoinFetchContactById(id);

        return employee;
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }
}
