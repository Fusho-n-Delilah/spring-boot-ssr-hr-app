package com.yogihr.controllers;

import com.yogihr.dtos.WebContact;
import com.yogihr.dtos.WebPTORequest;
import com.yogihr.dtos.WebTimeSheet;
import com.yogihr.dtos.WebWorkHours;
import com.yogihr.models.employee.Contact;
import com.yogihr.models.employee.Department;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.employee.Title;
import com.yogihr.models.payroll.*;
import com.yogihr.repositories.payroll.PayrollDAO;
import com.yogihr.services.EmployeeService;
import com.yogihr.services.PTORequestService;
import com.yogihr.services.PayrollService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Value("${states}")
    private List<String> states;
    private EmployeeService employeeService;
    private PTORequestService ptoRequestService;

    private PayrollService payrollService;

    public EmployeeController(EmployeeService employeeService,
                              PTORequestService ptoRequestService,
                              PayrollService payrollService){
        this.employeeService = employeeService;
        this.ptoRequestService = ptoRequestService;
        this.payrollService = payrollService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/contact")
    public String modifyContactInfo(Model theModel, HttpSession session){

        Employee employee = (Employee) session.getAttribute("employee");

        employee = employeeService.findEmployeeWithContactInfo(employee);

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("webContact", new WebContact(employee.getContact()));
        theModel.addAttribute("states", states);

        return "employee-contact-info";
    }

    @PostMapping("/modifyContact")
    public String saveNewContactInfo(@Valid @ModelAttribute("webContact") WebContact webContact,
                                     BindingResult bindingResult, HttpSession session, Model theModel){

        // get employee from the http sesh
        Employee employee = (Employee) session.getAttribute("employee");

        // if errors, send 'em back
        if(bindingResult.hasErrors()){
            return "employee-contact-info";
        }

        // no errors so update contact info
        employeeService.save(webContact);

        //send 'em back to the homepage if successful
        return "redirect:/home";
    }

    @GetMapping("/hrInfo")
    public String viewHrInfo(Model theModel, HttpSession session){
        //get the employee from the session
        Employee employee = (Employee) session.getAttribute("employee");

        //get department, title and manager info
        employee = employeeService.findEmployeeWithDepartmentInfo(employee);
        Department department = employee.getDepartments().get(employee.getDepartments().size()-1);
        Employee manager = department.getManagers().get(employee.getDepartments().get(0).getManagers().size() - 1);
        Title title = employee.getTitles().stream().filter(t->t.getToDate().equals(LocalDate.of(9999,1,1))).toList().get(0);

        // add the model attributes
        theModel.addAttribute("employee", employee);
        theModel.addAttribute("department", department);
        theModel.addAttribute("manager", manager);
        theModel.addAttribute("Title", title);

        return "employee-hr-info";
    }

    @GetMapping("/payRate")
    public String viewSalaryInfo(Model theModel, HttpSession session){
        //get employee details
        Employee employee = (Employee) session.getAttribute("employee");

        //get and attach salary info
        employee = employeeService.findEmployeeWithSalaryInfo(employee);
        List<Salary> salaries = employee.getSalaries();
        SalaryInfo salaryInfo = salaries.get(0).getSalaryInfo();
        System.out.println(salaries);
        int salary = salaries.stream()
                                .mapToInt(Salary::getSalary)
                                .sorted()
                                .boxed()
                                .toList()
                                .get(salaries.size()-1);

        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String salaryFormatted = usFormat.format(salary);

        //add the employee and Salary to the model to the model
        theModel.addAttribute("salaryInfo", salaryInfo);
        theModel.addAttribute("salary", salaryFormatted);

        return "employee-pay-rate";
    }

    @GetMapping("/timeOff")
    public String requestTimeOff(Model theModel, HttpSession session){
        //get employee details
        Employee employee = (Employee) session.getAttribute("employee");
        int employeeId = employee.getId();

        //get PTO Requests (may return an empty list) and add them to the model
        List<PTORequest> employeePtoRequests = ptoRequestService.findAllByEmpIdSortApprovedDesc(employeeId);

        if(employeePtoRequests != null){
            theModel.addAttribute("requests", employeePtoRequests);
        }

        //add the employee, an empty webPtoRequest DTO
        theModel.addAttribute("employee", employee);
        theModel.addAttribute("WebPTORequest", new WebPTORequest());

        return "time-off";
    }

    @PostMapping("/processTimeOffRequest")
    public String processTimeOffRequest(
            @Valid @ModelAttribute("WebPTORequest") WebPTORequest ptoRequest,
            BindingResult bindingResult,
            Model theModel, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        int id = ptoRequest.getEmployeeNumber();
        LocalDate fromDate = ptoRequest.getFromDate();


        //form validation
        if(bindingResult.hasErrors()){
            return "time-off";
        }

        // check the database if there is a request for that date already
        PTORequest existing = null;
        try {
            existing = ptoRequestService.findByEmpIdAndFromDate(id, fromDate);
        } catch (jakarta.persistence.NoResultException e) {
            System.out.println("no Request found");
        }
        if(existing != null){
            theModel.addAttribute("ptoRequest", new WebPTORequest());
            theModel.addAttribute("ptoRequestError", "Request for that date already exists");

            return "time-off";
        }

        // create request and store it in the database
        ptoRequest.setEmployeeNumber(employee.getId());
        ptoRequestService.save(ptoRequest);

        return "redirect:/employee/timeOff";
    }

    @GetMapping("/timeSheet")
    public String editTimeSheet(Model theModel, HttpSession session){

        //get the employee
        Employee employee = (Employee) session.getAttribute("employee");

        //get the payperiod
        PayPeriod payPeriod = payrollService.findPayPeriodByCurrentDate();

        // init web Workhours
        List<WebWorkHours> workHours = new ArrayList<>();

        //Check if they have a submitted timesheet
        List<TimeSheet> existingTimesheets = payrollService.findTimeSheetByEmployeeIdAndPayPeriod(employee.getId(), payPeriod.getId());

        //if there isn't one create a blank one, else fill with the existing data
        if(existingTimesheets.isEmpty()){
            System.out.println("no timesheet found");

            //create web work hours and timesheet
            LocalDate start = payPeriod.getFromDate();
            LocalDate end = payPeriod.getToDate();

            while (!start.isAfter(end)){
                workHours.add(new WebWorkHours(employee.getId(), start, payPeriod.getId()));

                start = start.plusDays(1);
            }
        } else {
            System.out.println("There's an existing timesheet");
            System.out.println(existingTimesheets.get(0));
            System.out.println(existingTimesheets.get(0).getWorkHours());

            //get list of WorkHours
            List<WorkHours> dbWorkHours = existingTimesheets.get(0).getWorkHours();

            dbWorkHours.forEach(wh -> {
                workHours.add(new WebWorkHours(wh.getEmployeeId(), wh.getDate(), wh.getHours(), wh.getPayPeriod()));
            });
        }

        // int
        WebTimeSheet timeSheet = new WebTimeSheet(employee.getId(), payPeriod.getId(), workHours);

        //add to the model
        theModel.addAttribute("webTimeSheet", timeSheet);
        theModel.addAttribute("employee", employee);
        theModel.addAttribute("week1", ("Week of " + payPeriod.getFromDate().getMonthValue() + "/" + payPeriod.getFromDate().getDayOfMonth()));
        theModel.addAttribute("week2", ("Week of " + payPeriod.getFromDate().getMonthValue() + "/" + payPeriod.getFromDate().plusDays(7).getDayOfMonth()));

        return "update-timesheet";
    }

    @PostMapping("/submitTimeSheet")
    public String submitTimeSheet(@Valid @ModelAttribute("webTimeSheet") WebTimeSheet timeSheet,
                                  BindingResult bindingResult,
                                  Model theModel){

        //form validation
        if(bindingResult.hasErrors()){
            //add the dates for headers to the model
            PayPeriod payPeriod = payrollService.findPayPeriodByCurrentDate();
            theModel.addAttribute("week1", ("Week of " + payPeriod.getFromDate().getMonthValue() + "/" + payPeriod.getFromDate().getDayOfMonth()));
            theModel.addAttribute("week2", ("Week of " + payPeriod.getFromDate().getMonthValue() + "/" + payPeriod.getFromDate().plusDays(7).getDayOfMonth()));

            //return to the view
            return "update-timesheet";
        }

        payrollService.save(timeSheet);

        return "redirect:/home";
    }
}
