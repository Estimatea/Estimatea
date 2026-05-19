package com.example.estimatea.controller;


import com.example.estimatea.model.Employee;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.service.EmployeeService;
import com.example.estimatea.service.SubProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subproject")
public class SubProjectController {

    private final SubProjectService subProjectService;
    private final EmployeeService employeeService;

    public SubProjectController(SubProjectService subProjectService, EmployeeService employeeService) {
        this.subProjectService = subProjectService;
        this.employeeService = employeeService;
    }

    //SPECIFIC SUBPROJECT VIEW
    @GetMapping("/{subProjectId}")
    public String viewSubProject(@PathVariable String subProjectId, Model model) {
        SubProject subProject = subProjectService.findSubProjectById(Integer.parseInt(subProjectId));
        model.addAttribute("subProject", subProject);

        List<Employee> employeeList = employeeService.getAllEmployeesForSubproject(Integer.parseInt(subProjectId));
        model.addAttribute("employeeList", employeeList);

        return "viewsubproject";
    }

    //CREATE SUBPROJECT
    @GetMapping("/create")
    public String createSubProject(Model model) {
        SubProject subProject = new SubProject();
        model.addAttribute("subProject", subProject);
        return "createsubproject";
    }

        @PostMapping("/create/save")
        public String saveCreatedSubProject(@ModelAttribute("subProject") SubProject subProject) {
            subProjectService.createSubproject(subProject);
            return "redirect:/projects/" + subProject.getProjectId();
        }


    //EDIT SUBPROJECT
    @GetMapping("/{subProjectId}/edit")
    public String editSubProject(@PathVariable String subProjectId, Model model) {
        SubProject subProject = subProjectService.findSubProjectById(Integer.parseInt(subProjectId));
        model.addAttribute("subProject", subProject);
        return "editsubproject";
    }

        @PostMapping("/save")
        public String saveSubProjectChanges(@ModelAttribute("subproject") SubProject subProject) {
            subProjectService.editSubProject(subProject);
            return "redirect:/subproject/" + subProject.getSubId();
        }

    //DELETE SUBPROJECT
    @PostMapping("/{subprojectId}/delete")
    public String deleteSubProject(@PathVariable int subprojectId) {
        SubProject sub = subProjectService.findSubProjectById(subprojectId);
        subProjectService.DeleteSubproject(subprojectId);
        return "redirect:/projects/" + sub.getProjectId();
    }



}
