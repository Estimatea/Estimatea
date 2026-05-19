package com.example.estimatea.controller;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
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

            @PostMapping("/logout")
            public String logout(HttpSession session) {
                session.invalidate();
                return "redirect:/login";
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
public String addEmployeeToProjectForm(Model model) {
    List<Employee> employeeList = employeeService.getAllEmployeeInCompany();
    model.addAttribute("employeeList", employeeList);
    return "add-employee-to-project";
}
        @PostMapping("/add/project")
           public String employeeAddedToProject(@RequestParam int employeeId, @RequestParam int projectId) {
               employeeService.addEmployeeToProject(employeeId, projectId);
               return "redirect:/project";
           }

// Remove employee from Main project
@PostMapping("/project/remove")
public String removeEmployeeFromProject(@RequestParam int employeeId, @RequestParam int projectId) {
    employeeService.removeEmployeeFromProject(employeeId, projectId);
    return "redirect:/project";
}

        // Employee handling on Subproject

// Add employee to Subproject
@PostMapping("/add/subproject")
public String employeeAddToSubProject(@RequestParam int employeeId, @RequestParam int subProjectId, @RequestParam int projectId) {
    employeeService.addEmployeeToSubProject(employeeId, subProjectId, projectId);
    return "redirect:/subprojects";
}

// Remove employee from Subproject
@PostMapping("/subproject/remove")
public String removeEmployeeFromSubProject(@RequestParam int employeeId, @RequestParam int subProjectId) {
    employeeService.removeEmployeeFromSubProject(employeeId, subProjectId);
    return "redirect:/subprojects";
}





//    @GetMapping("/subproject/remove")
//    public String removeEmployeeFromSubProjectForm(@RequestParam int employeeId, @RequestParam int subProjectId, Model model) {
//        List<Employee> subProjectEmployeeList = employeeService.getAllEmployeesForSubproject(subProjectId);
//        model.addAttribute("subProjectEmployeeList", subProjectEmployeeList);
//        return "remove-employee-from-subproject";
//    }

//    @GetMapping("/add/subproject")
//    public String addToSubProjectForm(@RequestParam int projectId, Model model) {
//        List<Employee> subProjectEmpList = employeeService.getAllEmployeesByProjectId(projectId);
//        model.addAttribute("subProjectEmpList", subProjectEmpList);
//        return "add-employee-to-subproject";
//    }
}
//@GetMapping("/project/remove")
//public String removeEmployeeFromProjectForm(@RequestParam int projectId, Model model) {
//    List<Employee> projectEmpList = employeeService.getAllEmployeesByProjectId(projectId);
//    model.addAttribute("projectEmployeeList", projectEmpList);
//    return "remove-employee-from-project";
//}