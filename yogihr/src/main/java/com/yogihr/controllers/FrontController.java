package com.yogihr.controllers;

import com.yogihr.models.Employee;
import com.yogihr.models.User;
import com.yogihr.services.EmployeeService;
import com.yogihr.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    private EmployeeService employeeService;

    @Autowired
    public FrontController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String showHome(Model theModel, HttpSession session) {
        User user = (User) session.getAttribute("user");

        Employee employee = user.getEmployee();

        theModel.addAttribute("employee", employee);

        return "home";
    }
}
