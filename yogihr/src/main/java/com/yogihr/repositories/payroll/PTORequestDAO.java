package com.yogihr.repositories.payroll;

import com.yogihr.models.payroll.PTORequest;

import java.time.LocalDate;
import java.util.List;

public interface PTORequestDAO {
    void save(PTORequest request);

//    void delete(PTORequest request);

    List<PTORequest> findAll();

    List<PTORequest> findAllUnapproved();

    List<PTORequest> findAllByEmpIdSortApprovedDesc(int id);

    PTORequest findByEmpIdAndFromDate(int id, LocalDate fromDate);

}
