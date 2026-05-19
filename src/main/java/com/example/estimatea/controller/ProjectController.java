package com.example.estimatea.controller;


import com.example.estimatea.exception.IllegalArgumentException;
import com.example.estimatea.exception.NotFoundException;
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

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final SubProjectService subProjectService;
    private final TaskService taskService;

    public ProjectController(ProjectService projectService,  EmployeeService employeeService, SubProjectService subProjectService,  TaskService taskService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.subProjectService = subProjectService;
    }

    // ALL PROJECTS VIEW
    @GetMapping("/all")
    public String allProjects(Model model) {
        try {
            List<Project> projectList = projectService.listAllProjects();
            model.addAttribute("projectList", projectList);
            return "allprojects";
        } catch (NotFoundException e) {
            model.addAttribute("error", e);
            return "error";
        }
    }

    //SPECIFIC PROJECT VIEW
    @GetMapping("/{projectId}")
    public String project(@PathVariable String projectId, Model model) {
        try {
            Project project = projectService.findProjectById(Integer.parseInt(projectId));
            model.addAttribute("project", project);

            List<Employee> employeeList = employeeService.getAllEmployeesByProjectId(Integer.parseInt(projectId));
            model.addAttribute("employeeList", employeeList);

            List<SubProject> subProjectList = subProjectService.findSubProjectsByProjectId(Integer.parseInt(projectId));
            model.addAttribute("subProjectList", subProjectList);

            List<Task> taskList = taskService.getTasksByProjectId(Integer.parseInt(projectId));
            model.addAttribute("taskList", taskList);

            return "viewproject";
        } catch (NotFoundException e) {
            model.addAttribute("error", e);
            return "error";
        }
    }

    //EDIT SPECIFIC PROJECT
    @GetMapping("/{projectId}/edit")
    public String editProject(@PathVariable String projectId, Model model) {
        try {
            Project project = projectService.findProjectById(Integer.parseInt(projectId));
            model.addAttribute("project", project);
            return "editproject";
        } catch (NotFoundException e) {
            model.addAttribute("error", e);
            return "error";
        }
    }

        @PostMapping("/{projectId}/edit/save")
        public String saveProjectChanges(@ModelAttribute("project") Project project, Model model) {
            try {
                projectService.editProject(project);
                return "redirect:/projects/" + project.getProjectId();
            } catch (IllegalArgumentException | NotFoundException e) {
                model.addAttribute("error", e);
                return "error";
            }
        }


    //CREATE NEW PROJECT
    @GetMapping("/create")
    public String createProject(Model model) {
        model.addAttribute("project", new Project());
        return "createproject";
    }

        @PostMapping("/create/save")
        public String saveNewProject(@ModelAttribute("project") Project project, Model model) {
            try {
                projectService.createNewProject(project);
                return "redirect:/projects/all";
            } catch (NotFoundException e) {
                model.addAttribute("error", e);
                return "error";
            }
        }


    //DELETE PROJECT
    @PostMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable int projectId, Model model) {
        try {
            projectService.deleteProject(projectId);
            return "redirect:/projects/all";
        } catch (NotFoundException e) {
            model.addAttribute("error", e);
            return "error";
        }
    }
}
