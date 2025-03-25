package com.yogihr.controllers;

import com.yogihr.dtos.WebContact;
import com.yogihr.dtos.WebEmployee;
import com.yogihr.models.employee.Contact;
import com.yogihr.models.employee.Department;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.employee.Title;
import com.yogihr.models.payroll.*;
import com.yogihr.services.EmployeeService;
import com.yogihr.services.PTORequestService;
import com.yogihr.services.PayrollService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@Controller
@RequestMapping("/manage")
public class ManageController {
    @Value("${states}")
    private List<String> states;

    @Value("${pay_types}")
    private List<String> payTypes;

    @Value("${pay_frequencies}")
    private List<String> payFrequencies;

    @Value("${dol_statuses}")
    private List<String> dolStatuses;

    @Value("${genders}")
    private List<String> genders;

    @Value("${emp_types}")
    private List<String> empTypes;

    private EmployeeService employeeService;
    private PTORequestService ptoRequestService;

    private PayrollService payrollService;

    @Autowired
    public ManageController(EmployeeService employeeService,
                            PTORequestService ptoRequestService,
                            PayrollService payrollService){
        this.employeeService = employeeService;
        this.ptoRequestService = ptoRequestService;
        this.payrollService = payrollService;
    }

    // simple mapping to return all 1000 employees (or a searched list) to a basic responsive table design to start
    // replace with paginated design later (as this is really ineffcient, but i need to get moving on something)
    @GetMapping("/list")
    public String listEmployees(Model theModel, @RequestParam(required = false) String keyword){

        List<Employee> employees = new ArrayList<>();

        if(keyword == null){
            employees = employeeService.findAllEmployees();
        } else {
            employees = employeeService.findByKeywordContainingIgnoreCase(keyword);
            theModel.addAttribute("keyword", keyword);
        }

        //add the list to the model
        theModel.addAttribute("employees", employees);

        return "list-employees1";
    }

    @GetMapping("/employee")
    public String updateEmployee(@RequestParam("employeeId") int id, Model theModel){

        //get the employee
        Employee employee = employeeService.findEmployeeWithDetails(id);


        //add validation attributes and web employee to the model
        theModel.addAttribute("employee", employee);
        theModel.addAttribute("states",states);
        theModel.addAttribute("empTypes", empTypes);
        theModel.addAttribute("payTypes", payTypes);
        theModel.addAttribute("payFrequencies", payFrequencies);
        theModel.addAttribute("dolStatuses", dolStatuses);
        theModel.addAttribute("genders", genders);
        theModel.addAttribute("employeeId", employee.getId());
        theModel.addAttribute("webEmployee", new WebEmployee(employee));

        return "update-employee";
    }

    @PostMapping("/modifyEmployeeContact")
    public String updateEmployeeContactInfo(@Valid @ModelAttribute("webEmployee") WebEmployee webEmployee,
                                            BindingResult bindingResult, Model theModel){

        // get employee from the database
        Employee employee = employeeService.findEmployeeWithDetails(webEmployee.getId());

        // if errors, send 'em back
        if(bindingResult.hasErrors()){
            return "update-employee";
        }

        //no errors so we save/merge
        employeeService.save(webEmployee);

        //send 'em back to the list of employees if successful
        return "redirect:/manage/list";
    }

    @GetMapping("timeOff")
    public String viewTimeOff(Model theModel){
        List<PTORequest> ptoRequests = ptoRequestService.findAllUnapproved();

        if (ptoRequests == null){
            ptoRequests = new ArrayList<>();
        }

        theModel.addAttribute("requests", ptoRequests);

        return "list-time-off-requests";
    }

    @GetMapping("/approveDenyTimeOff")
    public String approveDenyTimeOff(@RequestParam("ptoRequestId") int requestId, @RequestParam("approved") boolean approved){
        PTORequest ptoRequest = ptoRequestService.findById(requestId);

        if(!approved){
            ptoRequestService.denyRequest(ptoRequest);
            return "redirect:/manage/timeOff";
        }

        ptoRequest.setApproved(1);
        ptoRequestService.save(ptoRequest);

        return "redirect:/manage/timeOff";
    }

    @GetMapping("/timeSheets")
    public String viewUnapprovedTimeSheets(Model theModel){
        // get the unapproved time sheets
        List<TimeSheet> unapprovedTimesheets = payrollService.findAllUnapprovedTimeSheets();

        // init employee List and total hours list
        List<Employee> employees = new ArrayList<>();
        List<Double> totalHoursList = new ArrayList<>();

        // get corresponding employees
        unapprovedTimesheets.forEach(timeSheet -> {
            //get the associated employee for display and maintain the insertion order
            Employee emp = employeeService.findById(timeSheet.getEmployeeId());
            employees.add(employeeService.findEmployeeWithContactInfo(emp));

            //stream to get the total hours of workhours object in timesheet
            double totalHours = timeSheet.getWorkHours().stream()
                                                    .mapToDouble(WorkHours::getHours)
                                                    .sum();
            //add the total hours to the list
            totalHoursList.add(totalHours);
        });

        // add timesheets to the model
        theModel.addAttribute("timesheets", unapprovedTimesheets);
        theModel.addAttribute("employees", employees);
        theModel.addAttribute("totalHours", totalHoursList);

        return "list-unapproved-timesheets";
    }

    @GetMapping("/viewTimeSheet")
    public String viewTimeSheet(@RequestParam("timeSheetId") int id, Model theModel){

        // get Time Sheet and hours
        TimeSheet timeSheet = payrollService.findTimeSheetById(id);

        // get employee
        Employee employee = employeeService.findById(timeSheet.getEmployeeId());

        // get pay period
        PayPeriod payPeriod = payrollService.findPayPeriodById(timeSheet.getPayPeriod());

        //get dates for headers and add to model
        WorkHours week1Start = timeSheet.getWorkHours().get(0);
        WorkHours week2Start = timeSheet.getWorkHours().get(7);

        theModel.addAttribute("week1", ("Week of " + week1Start.getDate().getMonthValue() + "/" + week1Start.getDate().getDayOfMonth()));
        theModel.addAttribute("week2", ("Week of " + week2Start.getDate().getMonthValue() + "/" + week2Start.getDate().getDayOfMonth()));

        // add timesheet, employee, and pay period to the model
        theModel.addAttribute("timesheet", timeSheet);
        theModel.addAttribute("employee", employee);
        theModel.addAttribute("payPeriod", payPeriod);

        return "view-timesheet";
    }

    @GetMapping("/approveTimeSheet")
    public String approveTimeSheet(@RequestParam("timeSheetId") int id,
                                    Model theModel, HttpSession session){

        // get time sheet by id
        TimeSheet timeSheet = payrollService.findTimeSheetById(id);

        //get approver aka the hr employee logged in
        Employee approver = (Employee) session.getAttribute("employee");

        // approve and save timesheet
        payrollService.saveApprovedTimeSheet(approver, timeSheet);

        return "redirect:/manage/timeSheets";
    }
}
