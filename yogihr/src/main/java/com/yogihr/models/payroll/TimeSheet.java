
package com.yogihr.models.payroll;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "time_sheet")
public class TimeSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emp_no")
    private int employeeId;

    @Column(name = "pay_period_id")
    private int payPeriod;

    @Column(name = "submitted_date")
    private LocalDate submittedDate;

    @Column(name = "approver_id")
    private int approverId;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @OneToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinColumn(name = "time_sheet_id")
    private List<WorkHours> workHours;

    public TimeSheet() {
    }

    public TimeSheet(int employeeId, int payPeriod, LocalDate submittedDate) {
        this.employeeId = employeeId;
        this.payPeriod = payPeriod;
        this.submittedDate = submittedDate;
    }

    public TimeSheet(int employeeId, int payPeriod, LocalDate submittedDate, int approverId, LocalDate approvedDate) {
        this.employeeId = employeeId;
        this.payPeriod = payPeriod;
        this.submittedDate = submittedDate;
        this.approverId = approverId;
        this.approvedDate = approvedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(int payPeriod) {
        this.payPeriod = payPeriod;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public List<WorkHours> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkHours> workHours) {
        this.workHours = workHours;
    }

    public void add(WorkHours tempWorkHours){
        if(workHours == null){
            workHours = new ArrayList<>();
        }

        workHours.add(tempWorkHours);
    }

    @Override
    public String toString() {
        return "TimeSheet{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", payPeriod=" + payPeriod +
                ", submittedDate=" + submittedDate +
                ", approverId=" + approverId +
                ", approvedDate=" + approvedDate +
                ", workHours=" + workHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSheet timeSheet = (TimeSheet) o;
        return id == timeSheet.id && employeeId == timeSheet.employeeId && payPeriod == timeSheet.payPeriod && approverId == timeSheet.approverId && Objects.equals(submittedDate, timeSheet.submittedDate) && Objects.equals(approvedDate, timeSheet.approvedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, payPeriod, submittedDate, approverId, approvedDate);
    }
}
