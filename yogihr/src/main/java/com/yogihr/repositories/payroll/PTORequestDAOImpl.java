package com.yogihr.repositories.payroll;

import com.yogihr.models.payroll.PTORequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PTORequestDAOImpl implements PTORequestDAO{

    private EntityManager entityManager;

    @Autowired
    public PTORequestDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(PTORequest request) {
        entityManager.merge(request);
    }

    @Override
    public PTORequest findById(int id) {
        return entityManager.find(PTORequest.class, id);
    }

    @Override
    public List<PTORequest> findAll() {
        TypedQuery<PTORequest> query = entityManager.createQuery(
                "SELECT r FROM PTORequest r", PTORequest.class);

        return query.getResultList();
    }

    @Override
    public List<PTORequest> findAllUnapproved() {
        TypedQuery<PTORequest> query = entityManager.createQuery(
                "SELECT r FROM PTORequest r "
                        + "WHERE r.approved = 0", PTORequest.class);

        return query.getResultList();
    }

    @Override
    public List<PTORequest> findAllApproved() {
        TypedQuery<PTORequest> query = entityManager.createQuery(
                "SELECT r FROM PTORequest r "
                        + "WHERE r.approved = 1", PTORequest.class);

        return query.getResultList();
    }

    @Override
    public List<PTORequest> findAllByEmpIdSortApprovedDesc(int id) {
        TypedQuery<PTORequest> query = entityManager.createQuery(
                "SELECT r FROM PTORequest r "
                + "WHERE r.employeeId = :data "
                + "ORDER BY r.approved DESC", PTORequest.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public List<PTORequest> findAllApprovedByEmpIDAndDateRange(int id, LocalDate fromDate, LocalDate toDate) {
        //this is an inherently bugged query
        // what if the pto request spans weekends, or multiple pay periods? Solve this problem later
        // test the happy path for now

        TypedQuery<PTORequest> query = entityManager.createQuery(
                "SELECT r FROM PTORequest r "
                        + "WHERE r.employeeId = :data "
                        + "AND ((r.fromDate BETWEEN :fromDate AND :toDate) OR (r.toDate BETWEEN :fromDate AND :toDate))", PTORequest.class);
        query.setParameter("data", id);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);

        return query.getResultList();
    }


    @Override
    public PTORequest findByEmpIdAndFromDate(int id, LocalDate fromDate) throws jakarta.persistence.NoResultException{

        TypedQuery<PTORequest> query = entityManager.createQuery(
                "SELECT r FROM PTORequest r "
                        + "WHERE r.employeeId = :data "
                        + "AND r.fromDate = :date", PTORequest.class);
        query.setParameter("data", id);
        query.setParameter("date", fromDate);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void delete(PTORequest ptoRequest) {
        entityManager.remove(ptoRequest);
    }
}
