package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String login(@RequestParam String employeeUsername, @RequestParam String employeePassword, HttpSession session, Model model) {
        try {
               Employee employee = employeeService.employeeLogin(employeeUsername, employeePassword);
               session.setAttribute("currentEmployee", employee);
               return "redirect:/projects/all";

             } catch (Exception e) {
                 model.addAttribute("loginError", e.getMessage());
                 return "login-page";
             }
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
    @GetMapping("/add/{projectId}")
    public String addEmployeeToProjectForm(@PathVariable int projectId, Model model) { // GET
        List<Employee> employeeList = employeeService.employeesNotInProject(projectId);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("projectId", projectId);
        return "add-employee-to-project";
    }

            @PostMapping("/add/{projectId}") // POST
            public String employeeAddedToProject(@PathVariable int projectId, @RequestParam int employeeId) {
                employeeService.addEmployeeToProject(employeeId, projectId);
                return "redirect:/projects/" + projectId;
            }

    // Remove employee from Main project
    @GetMapping("/remove/{projectId}")
    public String removeEmployeeFromProjectFrom(Model model,  @PathVariable int projectId) {
        List<Employee> employeeList = employeeService.getAllEmployeesByProjectId(projectId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("employeeList", employeeList);
        return "remove-employee-from-project";
    }

            @PostMapping("/remove/{projectId}") // POST
            public String removeEmployeeFromProject(@RequestParam int employeeId, @PathVariable int projectId) {
                employeeService.removeEmployeeFromProject(employeeId, projectId);
                return "redirect:/projects/" + projectId;
            }


        // Employee handling on Subproject

    // Add employee to Subproject
    @GetMapping("/add/subproject") // GET
    public String addEmployeeToSubProjectForm(@RequestParam int projectId, @RequestParam int subProjectId, Model model) {
        List<Employee> subProjectEmpList = employeeService.getAllEmployeeViableToAddToSubProject(subProjectId, projectId);
        model.addAttribute("subProjectEmployeeList", subProjectEmpList);
        model.addAttribute("projectId", projectId);
        model.addAttribute("subProjectId", subProjectId);

        return "add-employee-to-subproject";
    }

            @PostMapping("/add/subproject") // POST
            public String employeeAddedToSubProject(@RequestParam int employeeId, @RequestParam int subProjectId, @RequestParam int projectId) {
                employeeService.addEmployeeToSubProject(employeeId, subProjectId, projectId);
                return "redirect:/subproject/" + subProjectId;
            }


    // Remove employee from Subproject
    @GetMapping("/remove/subproject")
    public String removeEmployeeFromSubprojectFrom(@RequestParam int projectId, @RequestParam int subProjectId, Model model) {
        List<Employee> subProjectemployeeList = employeeService.getAllEmployeesForSubproject(subProjectId);
        model.addAttribute("subProjectEmployeeList", subProjectemployeeList);
        model.addAttribute("projectId", projectId);
        model.addAttribute("subProjectId", subProjectId);

        return "remove-employee-from-subproject";
    }

            @PostMapping("/remove/subproject") // POST
            public String removeEmployeeFromSubProject(@RequestParam int employeeId,@RequestParam int subProjectId, @RequestParam int projectId) {
                employeeService.removeEmployeeFromSubProject(employeeId, subProjectId);
                return "redirect:/subproject/" + subProjectId;
            }
}
