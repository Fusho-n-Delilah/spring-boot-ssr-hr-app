package com.yogihr.controllers;

import com.yogihr.dtos.WebContact;
import com.yogihr.dtos.WebPTORequest;
import com.yogihr.models.employee.Contact;
import com.yogihr.models.employee.Department;
import com.yogihr.models.employee.Employee;
import com.yogihr.models.employee.Title;
import com.yogihr.models.payroll.PTORequest;
import com.yogihr.models.payroll.Salary;
import com.yogihr.models.payroll.SalaryInfo;
import com.yogihr.services.EmployeeService;
import com.yogihr.services.PTORequestService;
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

    public EmployeeController(EmployeeService employeeService, PTORequestService ptoRequestService){
        this.employeeService = employeeService;
        this.ptoRequestService = ptoRequestService;
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

        //no errors, so lets set the employee's contact to the new contact
        employee.setContact(new Contact(employee.getId(),
                                        webContact.getWorkEmail(),
                                        webContact.getPersonalEmail(),
                                        webContact.getPhoneNumber(),
                                        webContact.getStreetAddress(),
                                        webContact.getApt(),
                                        webContact.getCity(),
                                        webContact.getState(),
                                        webContact.getPostalCode()));

        //now let's persist the employee
        employeeService.save(employee);

        //add the employee to the model
//        theModel.addAttribute("employee", employee);

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
}
