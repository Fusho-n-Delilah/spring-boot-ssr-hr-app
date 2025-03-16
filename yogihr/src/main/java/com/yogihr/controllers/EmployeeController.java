package com.yogihr.controllers;

import com.yogihr.dtos.WebContact;
import com.yogihr.models.Contact;
import com.yogihr.models.Employee;
import com.yogihr.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Value("${states}")
    private List<String> states;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
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
}
