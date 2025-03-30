package com.yogihr.repositories.payroll;

import com.yogihr.models.payroll.Deductions;
import com.yogihr.models.payroll.PayCheck;
import com.yogihr.models.payroll.PayPeriod;
import com.yogihr.models.payroll.TimeSheet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PayrollDAOImpl implements PayrollDAO{

    private EntityManager entityManager;

    public PayrollDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public TimeSheet findTimeSheetById(int id) {
        TypedQuery<TimeSheet> query = entityManager.createQuery(
                "Select t From TimeSheet t "
                        + "JOIN FETCH t.workHours "
                        + "Where t.id = :data ", TimeSheet.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public TimeSheet save(TimeSheet timeSheet) {
        TimeSheet newTimesheet = entityManager.merge(timeSheet);
        return newTimesheet;
    }

    @Override
    public PayPeriod findPayPeriodById(int id) {
        return entityManager.find(PayPeriod.class, id);
    }

    @Override
    public PayPeriod findPayPeriodByCurrentDate() {
        TypedQuery<PayPeriod> query = entityManager.createQuery(
                "Select p From PayPeriod p "
                        + "Where CURRENT_DATE BETWEEN p.fromDate AND p.toDate", PayPeriod.class
        );

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(PayPeriod payPeriod) {
        entityManager.merge(payPeriod);
    }

    @Override
    public Deductions findDeductionsByEmpId(int id) {
        TypedQuery<Deductions> query = entityManager.createQuery(
                "Select d From Deductions d "
                        + "Where d.id = :data", Deductions.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Deductions deductions) {
        entityManager.merge(deductions);
    }

    @Override
    @Transactional
    public void save(PayCheck payCheck) {
        entityManager.merge(payCheck);
    }

    @Override
    public List<PayPeriod> findPayPeriodsByYear(int year) {
        TypedQuery<PayPeriod> query = entityManager.createQuery(
                "Select p from PayPeriod p "
                        + "Where p.year = :year", PayPeriod.class);
        query.setParameter("year", year);


        return query.getResultList();
    }

    @Override
    public List<Integer> getPayPeriodYears() {
        List<Integer> payYears = entityManager.createQuery(
                "Select DISTINCT(p.year) from PayPeriod p "
                        + "Order by p.year", Integer.class).getResultList();

        return payYears;
    }

    @Override
    public List<TimeSheet> findTimeSheetAndWorkhoursByEmployeeIdAndPayPeriod(int id, int payPeriodId) {
        TypedQuery<TimeSheet> query = entityManager.createQuery(
                "Select t From TimeSheet t "
                        + "JOIN FETCH t.workHours "
                        + "Where t.employeeId = :data "
                        + "AND t.payPeriod = :payPeriod", TimeSheet.class);
        query.setParameter("data", id);
        query.setParameter("payPeriod", payPeriodId);

        return query.getResultList();
    }

    @Override
    public List<TimeSheet> findAllUnapprovedTimeSheets(int payPeriodId) {
        TypedQuery<TimeSheet> query = entityManager.createQuery(
                "Select t from TimeSheet t "
                            + "JOIN FETCH t.workHours "
                            + "Where t.approvedDate IS NULL "
                            + "AND t.payPeriod = :payPeriod "
                            + "Order By t.employeeId", TimeSheet.class);
        query.setParameter("payPeriod", payPeriodId);

        return query.getResultList();
    }

    @Override
    public List<TimeSheet> findAllApprovedTimeSheets(int payPeriodId) {
        TypedQuery<TimeSheet> query = entityManager.createQuery(
                "Select t from TimeSheet t "
                        + "JOIN FETCH t.workHours "
                        + "Where t.approverId > 0 "
                        + "AND t.payPeriod = :payPeriod", TimeSheet.class);
        query.setParameter("payPeriod", payPeriodId);

        return query.getResultList();
    }

    @Override
    public PayCheck findPayCheckById(int id) {
        TypedQuery<PayCheck> query = entityManager.createQuery(
                "Select p from PayCheck p "
                        + "Where p.id = :data", PayCheck.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public List<PayCheck> findAllPayChecksByEmpID(int id) {
        TypedQuery<PayCheck> query = entityManager.createQuery(
               "Select p from PayCheck p "
                        + "Where p.employeeId = :data "
                        + "Order By p.checkDate", PayCheck.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public List<PayCheck> findAllPayChecksByEmpIDAndYear(int id, int year) {
        TypedQuery<PayCheck> query = entityManager.createQuery(
                "Select p from PayCheck p "
                        + "Where p.employeeId = :data "
                        + "And p.year = :year "
                        + "Order By p.checkDate", PayCheck.class);
        query.setParameter("data", id);
        query.setParameter("year", year);

        return query.getResultList();
    }
}
