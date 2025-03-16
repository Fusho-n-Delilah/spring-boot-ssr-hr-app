package com.yogihr.repositories;

import com.yogihr.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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

    @Override
    public Employee findEmployeeJoinFetchContactById(int id) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e JOIN FETCH e.contact WHERE e.id = :data", Employee.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        entityManager.merge(employee);
    }

}
