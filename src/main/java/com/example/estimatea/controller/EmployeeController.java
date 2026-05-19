package com.example.estimatea.controller;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

        // EMPLOYEE LOGIN
    @GetMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        try {
            Employee employee = employeeService.employeeLogin(email, password);
            session.setAttribute("currentEmployee", employee);
            return "redirect:/homepage";
        } catch (NotFoundException e) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

        // Full list of employees in organization
    @GetMapping("/all")
    public String getAllEmployees(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployeeInCompany();
        model.addAttribute("employees", employeeList);

        return "employee-list";
    }
}
