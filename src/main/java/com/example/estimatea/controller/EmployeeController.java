package com.example.estimatea.controller;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String loginForm() {
        return "login-page";
    }

    @PostMapping("/login")
    public String login(@RequestParam String employeeUsername, @RequestParam String employeePassword, HttpSession session) {
            Employee employee = employeeService.employeeLogin(employeeUsername, employeePassword);
            session.setAttribute("currentEmployee", employee);
            return "redirect:/projects/all";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/employee/login";
    }

        // Full list of employees in organization

    @GetMapping("/all")
    public String getAllEmployees(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployeeInCompany();
        model.addAttribute("employeeList", employeeList);
        return "employee-list";
    }

        // Employee handling on Main project

    // Add Employee to Main project
    @GetMapping("/add/project")
    public String addEmployeeToProjectForm(Model model) { // GET
        List<Employee> employeeList = employeeService.getAllEmployeeInCompany();
        model.addAttribute("employeeList", employeeList);
        return "add-employee-to-project";
    }

            @PostMapping("/add/project") // POST
            public String employeeAddedToProject(@RequestParam int employeeId, @RequestParam int projectId) {
                employeeService.addEmployeeToProject(employeeId, projectId);
                return "redirect:/projects/" + projectId;
            }

    // Remove employee from Main project
    @PostMapping("/project/remove") // POST
    public String removeEmployeeFromProject(@RequestParam int employeeId, @RequestParam int projectId) {
        employeeService.removeEmployeeFromProject(employeeId, projectId);
        return "redirect:/project";
    }

        // Employee handling on Subproject

    // Add employee to Subproject
    @GetMapping("/add/subproject") // GET
    public String addEmployeeToSubProjectForm(@RequestParam int employeeId, @RequestParam int projectId, @RequestParam int subProjectId, Model model) {
        List<Employee> subProjectEmpList = employeeService.getAllEmployeesByProjectId(projectId);
        model.addAttribute("subProjectEmployeeList", subProjectEmpList);
        model.addAttribute("projectId", projectId);
        model.addAttribute("subProjectId", subProjectId);

        return "add-employee-to-subproject";
    }

            @PostMapping("add/subproject") // POST
            public String employeeAddedToSubProject(@RequestParam int employeeId, @RequestParam int subProjectId, @RequestParam int projectId) {
                employeeService.addEmployeeToSubProject(employeeId, subProjectId, projectId);
                return "redirect:/subprojects" + subProjectId;
            }

    // Remove employee from Subproject
    @PostMapping("/subproject/remove") // POST
    public String removeEmployeeFromSubProject(@RequestParam int employeeId, @RequestParam int subProjectId) {
        employeeService.removeEmployeeFromSubProject(employeeId, subProjectId);
        return "redirect:/subprojects";
    }
}
