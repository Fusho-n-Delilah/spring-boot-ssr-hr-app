package com.yogihr.repositories;

import com.yogihr.models.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }
}
