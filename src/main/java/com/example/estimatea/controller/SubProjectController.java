package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.model.Task;
import com.example.estimatea.service.EmployeeService;
import com.example.estimatea.service.ProjectService;
import com.example.estimatea.service.SubProjectService;
import com.example.estimatea.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/subproject")
public class SubProjectController {

    private final SubProjectService subProjectService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final TaskService taskService;

    public SubProjectController(SubProjectService subProjectService, ProjectService projectService, EmployeeService employeeService, TaskService taskService) {
        this.subProjectService = subProjectService;
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    //SPECIFIC SUBPROJECT VIEW
    @GetMapping("/{subProjectId}")
    public String viewSubProject(@PathVariable int subProjectId, Model model) {
        SubProject subProject = subProjectService.findSubProjectById(subProjectId);
        model.addAttribute("subProject", subProject);

        Project project = projectService.findProjectById(subProject.getProjectId());
        model.addAttribute("project", project);

        List<Employee> employeeList = employeeService.getAllEmployeesForSubproject(subProjectId);
        model.addAttribute("employeeList", employeeList);

        List<Task> taskList = taskService.getTasksForSubprojectId(subProjectId);
        model.addAttribute("taskList", taskList);

        return "view-subproject";
    }

    //CREATE SUBPROJECT
    @GetMapping("/project/{projectId}/create")
    public String createSubProject(@PathVariable int projectId, Model model) {
        SubProject subProject = new SubProject();
        subProject.setProjectId(projectId);
        model.addAttribute("subProject", subProject);
        return "create-subproject";
    }

        @PostMapping("/create/save")
        public String saveCreatedSubProject(@ModelAttribute("subProject") SubProject subProject) {
            subProjectService.createSubproject(subProject);
            return "redirect:/projects/" + subProject.getProjectId();
        }


    //EDIT SUBPROJECT
    @GetMapping("/{subProjectId}/edit")
    public String editSubProject(@PathVariable int subProjectId, Model model) {
        SubProject subProject = subProjectService.findSubProjectById(subProjectId);
        model.addAttribute("subProject", subProject);
        return "edit-subproject";
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

    // SET SUBPROJECT COMPLETED
    @PostMapping("/{subprojectId}/complete")
    public String completeSubProject(@PathVariable int subprojectId, RedirectAttributes redirectAttributes) {
        try {
            subProjectService.completeSubProject(subprojectId);
            int projectId = subProjectService.findSubProjectById(subprojectId).getProjectId();
            return "redirect:/projects/" + projectId;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/subproject/" + subprojectId + "/edit";
        }
    }
}
