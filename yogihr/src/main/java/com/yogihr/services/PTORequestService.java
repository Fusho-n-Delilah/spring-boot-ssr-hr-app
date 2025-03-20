package com.yogihr.services;


import com.yogihr.dtos.WebPTORequest;
import com.yogihr.models.payroll.PTORequest;

import java.time.LocalDate;
import java.util.List;

public interface PTORequestService {
    void save(WebPTORequest request);

//    void delete(PTORequest request);

    List<PTORequest> findAll();

    List<PTORequest> findAllUnapproved();

    List<PTORequest> findAllByEmpIdSortApprovedDesc(int id);

    PTORequest findByEmpIdAndFromDate(int id, LocalDate fromDate);
}
