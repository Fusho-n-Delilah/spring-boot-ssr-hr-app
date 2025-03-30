package com.yogihr.services;

import com.yogihr.dtos.WebPTORequest;
import com.yogihr.models.payroll.PTORequest;
import com.yogihr.repositories.payroll.PTORequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PTORequestServiceImpl implements PTORequestService{

    private PTORequestDAO ptoRequestDAO;

    @Autowired
    public PTORequestServiceImpl(PTORequestDAO ptoRequestDAO){
        this.ptoRequestDAO = ptoRequestDAO;
    }

    @Override
    public void save(WebPTORequest newRequest) {
        PTORequest ptoRequest =  new PTORequest();

        //set some default data like total Hours
        long daysOff = ChronoUnit.DAYS.between(newRequest.getFromDate(), newRequest.getToDate()) + 1;
        double totalHours = 8.0 * daysOff;

        //assign the webPtoRequest details to a PTORequest Object
        ptoRequest.setEmployeeId(newRequest.getEmployeeNumber());
        ptoRequest.setFromDate(newRequest.getFromDate());
        ptoRequest.setToDate(newRequest.getToDate());
        ptoRequest.setTotalHours(totalHours);
        ptoRequest.setApproved(0);


        ptoRequestDAO.save(ptoRequest);
    }

    @Override
    public void save(PTORequest request) {
        ptoRequestDAO.save(request);
    }

    @Override
    public PTORequest findById(int id) {
        return ptoRequestDAO.findById(id);
    }

    @Override
    public List<PTORequest> findAll() {
        return ptoRequestDAO.findAll();
    }

    @Override
    public List<PTORequest> findAllUnapproved() {
        return ptoRequestDAO.findAllUnapproved();
    }

    @Override
    public List<PTORequest> findAllByEmpIdSortApprovedDesc(int id) {
        return ptoRequestDAO.findAllByEmpIdSortApprovedDesc(id);
    }

    @Override
    public PTORequest findByEmpIdAndFromDate(int id, LocalDate fromDate) throws jakarta.persistence.NoResultException{
        return ptoRequestDAO.findByEmpIdAndFromDate(id, fromDate);
    }

    @Override
    public void denyRequest(PTORequest request) {
        ptoRequestDAO.delete(request);
    }


}
