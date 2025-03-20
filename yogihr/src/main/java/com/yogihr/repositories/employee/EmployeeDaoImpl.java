package com.yogihr.repositories.employee;

import com.yogihr.models.employee.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAllOrderByLastNameAsc() {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e "
                        + "JOIN FETCH e.contact", Employee.class
        );

        List<Employee> employeesSortedList = query.getResultList().stream()
                                                    .sorted(Comparator.comparing(Employee::getLastName))
                                                    .toList();


        return employeesSortedList;
    }

    @Override
    public List<Employee> findByKeywordContainingIgnoreCase(String keyword) {
        keyword = "%" + keyword + "%";

        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e "
                        + "JOIN FETCH e.contact "
                        + "WHERE CAST(e.id AS string) LIKE LOWER(:keyword) "
                        + "OR LOWER(e.contact.workEmail) LIKE LOWER(:keyword) "
                        + "OR Lower(e.firstName) LIKE LOWER(:keyword) "
                        + "OR Lower(e.lastName) LIKE LOWER(:keyword)", Employee.class
        );
        query.setParameter("keyword", keyword);

        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee findEmployeeJoinFetchAll(int id) {
        List<Employee> employees = new ArrayList<>();

        employees = entityManager.createQuery(
            "SELECT e FROM Employee e "
                        + "JOIN FETCH e.contact "
                        + "JOIN FETCH e.salaries "
                        + "WHERE e.id = :data", Employee.class)
        .setParameter("data", id)
        .getResultList();

        Employee employee = entityManager.createQuery(
                "SELECT e FROM Employee e "
                        + "JOIN FETCH e.departments "
                        + "WHERE e IN :employees", Employee.class)
        .setParameter("employees", employees)
        .getSingleResult();

        return employee;
    }

    @Override
    public Employee findEmployeeJoinFetchContactById(int id) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e JOIN FETCH e.contact WHERE e.id = :data", Employee.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public Employee findEmployeeJoinFetchDepartmentById(int id) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e "
                        + "JOIN FETCH e.departments "
                        + "WHERE e.id = :data", Employee.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public Employee findEmployeeJoinFetchTitleAndDepartmentById(int id) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e "
                        + "JOIN FETCH e.departments "
                        + "JOIN FETCH e.titles "
                        + "WHERE e.id = :data", Employee.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public Employee findEmployeeJoinFetchSalaryInfoById(int id) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e "
                        + "JOIN FETCH e.salaries "
                        + "WHERE e.id = :data", Employee.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        entityManager.merge(employee);
    }

}
