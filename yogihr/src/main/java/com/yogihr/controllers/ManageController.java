package com.yogihr.controllers;

import com.yogihr.dtos.WebContact;
import com.yogihr.dtos.WebEmployee;
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

    @Autowired
    public ManageController(EmployeeService employeeService, PTORequestService ptoRequestService){
        this.employeeService = employeeService;
        this.ptoRequestService = ptoRequestService;
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

        //save basic employee information
        employee.setFirstName(webEmployee.getFirstName());
        employee.setLastName(webEmployee.getLastName());
        employee.setBirthDate(webEmployee.getBirthDate());
        employee.setHireDate(webEmployee.getHireDate());

        //no errors, so lets save the Employee Contact Details
        employee.setContact(new Contact(employee.getId(),
                webEmployee.getWorkEmail(),
                webEmployee.getPersonalEmail(),
                webEmployee.getPhoneNumber(),
                webEmployee.getStreetAddress(),
                webEmployee.getApt(),
                webEmployee.getCity(),
                webEmployee.getState(),
                webEmployee.getPostalCode()));

        //set salary info
        Salary curSalary = employee.getSalaries().get(employee.getSalaries().size()-1);
        SalaryInfo curSalaryInfo = curSalary.getSalaryInfo();

        SalaryInfo webSalaryInfo = new SalaryInfo(employee.getId(),
                webEmployee.getEmployeeType(),
                webEmployee.getPayType(),
                webEmployee.getPayFreq(),
                webEmployee.getDolStatus(),
                webEmployee.getFedTax(),
                webEmployee.getMedTax(),
                webEmployee.getSocSecTax(),
                curSalaryInfo.getFromDate(),
                curSalaryInfo.getToDate()
        );

        if(curSalary.getSalary() != webEmployee.getSalary()){
//            curSalary.setToDate(Date.valueOf(LocalDate.now()));
//
//            Salary webSalary = new Salary(
//                    employee.getId(),
//                    webEmployee.getSalary(),
//                    Date.valueOf(LocalDate.now()),
//                    Date.valueOf("9999-01-01")
//            );
//            webSalary.setSalaryInfo(curSalaryInfo);
//
//            employee.addSalary(webSalary);
            curSalary.setSalary(webEmployee.getSalary());
        }

        if(!curSalaryInfo.equals(webSalaryInfo)){
            curSalaryInfo.setEmployeeType(webEmployee.getEmployeeType());
            curSalaryInfo.setPayType(webEmployee.getPayType());
            curSalaryInfo.setPayFreq(webEmployee.getPayFreq());
            curSalaryInfo.setDolStatus(webEmployee.getDolStatus());
            curSalaryInfo.setFedTax(webEmployee.getFedTax());
            curSalaryInfo.setMedTax(webEmployee.getMedTax());
            curSalaryInfo.setSocSecTax(webEmployee.getSocSecTax());
            curSalaryInfo.setFromDate(Date.valueOf(LocalDate.now()));
        }

        //set title if changed
        Title curTitle = employee.getTitles().get(employee.getTitles().size() - 1);

        if(!curTitle.getTitle().equals(webEmployee.getTitle())){
            curTitle.setToDate(LocalDate.now());

            Title webTitle = new Title(
                employee.getId(),
                webEmployee.getTitle(),
                LocalDate.now(),
                LocalDate.of(9999,01,01)
            );

            employee.addTitle(webTitle);
        }


        //now let's persist the employee
        employeeService.save(employee);

        //add the employee to the model
//        theModel.addAttribute("employee", employee);

        //send 'em back to the homepage if successful
        return "redirect:/manage/list";
    }

    @GetMapping("timeOff")
    public String viewTimeOff(Model theModel){
        List<PTORequest> ptoRequests = ptoRequestService.findAllUnapproved();

        if (ptoRequests == null){
            ptoRequests = new ArrayList<>();
        }

        theModel.addAttribute("requests", ptoRequests);

        return "view-time-off-requests";
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
}
