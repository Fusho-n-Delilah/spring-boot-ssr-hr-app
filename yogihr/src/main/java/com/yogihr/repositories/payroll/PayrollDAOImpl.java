package com.yogihr.repositories.payroll;

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
}
