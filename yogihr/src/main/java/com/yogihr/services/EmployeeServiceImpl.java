package com.yogihr.services;

import com.yogihr.dtos.WebContact;
import com.yogihr.dtos.WebEmployee;
import com.yogihr.models.employee.Contact;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.employee.Title;
import com.yogihr.models.payroll.Salary;
import com.yogihr.models.payroll.SalaryInfo;
import com.yogihr.repositories.employee.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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

    @Override
    public void save(WebEmployee webEmployee) {
        Employee employee = this.findEmployeeWithDetails(webEmployee.getId());

        //save basic employee information
        employee.setFirstName(webEmployee.getFirstName());
        employee.setLastName(webEmployee.getLastName());
        employee.setBirthDate(webEmployee.getBirthDate());
        employee.setHireDate(webEmployee.getHireDate());

        //no errors, so lets save the Employee Contact Details
        employee.setContact(new Contact(employee.getId(),
                webEmployee.getWorkEmail(),
                webEmployee.getPersonalEmail(),
                webEmployee.getPhoneNumber(),
                webEmployee.getStreetAddress(),
                webEmployee.getApt(),
                webEmployee.getCity(),
                webEmployee.getState(),
                webEmployee.getPostalCode()));

        //set salary info
        Salary curSalary = employee.getSalaries().get(employee.getSalaries().size()-1);
        SalaryInfo curSalaryInfo = curSalary.getSalaryInfo();

        SalaryInfo webSalaryInfo = new SalaryInfo(employee.getId(),
                webEmployee.getEmployeeType(),
                webEmployee.getPayType(),
                webEmployee.getPayFreq(),
                webEmployee.getDolStatus(),
                webEmployee.getFedTax(),
                webEmployee.getMedTax(),
                webEmployee.getSocSecTax(),
                curSalaryInfo.getFromDate(),
                curSalaryInfo.getToDate()
        );

        if(curSalary.getSalary() != webEmployee.getSalary()){
//            curSalary.setToDate(Date.valueOf(LocalDate.now()));
//
//            Salary webSalary = new Salary(
//                    employee.getId(),
//                    webEmployee.getSalary(),
//                    Date.valueOf(LocalDate.now()),
//                    Date.valueOf("9999-01-01")
//            );
//            webSalary.setSalaryInfo(curSalaryInfo);
//
//            employee.addSalary(webSalary);
            curSalary.setSalary(webEmployee.getSalary());
        }

        if(!curSalaryInfo.equals(webSalaryInfo)){
            curSalaryInfo.setEmployeeType(webEmployee.getEmployeeType());
            curSalaryInfo.setPayType(webEmployee.getPayType());
            curSalaryInfo.setPayFreq(webEmployee.getPayFreq());
            curSalaryInfo.setDolStatus(webEmployee.getDolStatus());
            curSalaryInfo.setFedTax(webEmployee.getFedTax());
            curSalaryInfo.setMedTax(webEmployee.getMedTax());
            curSalaryInfo.setSocSecTax(webEmployee.getSocSecTax());
            curSalaryInfo.setFromDate(Date.valueOf(LocalDate.now()));
        }

        //set title if changed
        Title curTitle = employee.getTitles().get(employee.getTitles().size() - 1);

        if(!curTitle.getTitle().equals(webEmployee.getTitle())){
            curTitle.setToDate(LocalDate.now());

            Title webTitle = new Title(
                    employee.getId(),
                    webEmployee.getTitle(),
                    LocalDate.now(),
                    LocalDate.of(9999,01,01)
            );

            employee.addTitle(webTitle);
        }


        //now let's persist the employee
        this.save(employee);
    }

    @Override
    public void save(WebContact webContact) {

        //find the employee with the contact info
        Employee employee = this.findById(webContact.getEmployeeId());
        employee = this.findEmployeeWithContactInfo(employee);

        //update contact info
        employee.setContact(new Contact(employee.getId(),
                webContact.getWorkEmail(),
                webContact.getPersonalEmail(),
                webContact.getPhoneNumber(),
                webContact.getStreetAddress(),
                webContact.getApt(),
                webContact.getCity(),
                webContact.getState(),
                webContact.getPostalCode()));

        this.save(employee);
    }
}
