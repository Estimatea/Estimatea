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
    public String getAllProjectsForm(Model model) {
            List<Project> projectList = projectService.listAllProjects();
            model.addAttribute("projectList", projectList);

            List<Employee> employeeList = employeeService.getAllEmployeeInCompany();
            model.addAttribute("employeeList", employeeList);

            return "all-projects";
    }

    //SPECIFIC PROJECT VIEW
    @GetMapping("/{projectId}")
    public String getProjectOverview(@PathVariable int projectId, Model model) {
            Project project = projectService.findProjectById(projectId);
            model.addAttribute("project", project);

            List<Employee> employeeList = employeeService.getAllEmployeesByProjectId(projectId);
            model.addAttribute("employeeList", employeeList);

            List<SubProject> subProjectList = subProjectService.findSubProjectsByProjectId(projectId);
            model.addAttribute("subProjectList", subProjectList);

            List<Task> taskList = taskService.getTasksByProjectId(projectId);
            model.addAttribute("taskList", taskList);

            return "view-project";
    }

    //EDIT SPECIFIC PROJECT
    @GetMapping("/{projectId}/edit")
    public String editProjectForm(@PathVariable int projectId, Model model) {
            Project project = projectService.findProjectById(projectId);
            model.addAttribute("project", project);
            return "edit-project";
    }

        @PostMapping("/{projectId}/edit/save")
        public String saveProjectChanges(@ModelAttribute("project") Project project) {
                projectService.editProject(project);
                return "redirect:/projects/" + project.getProjectId();
        }


    //CREATE NEW PROJECT
    @GetMapping("/create")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create-project";
    }

        @PostMapping("/create/save")
        public String saveNewProject(@ModelAttribute("project") Project project) {
                projectService.createNewProject(project);
                return "redirect:/projects/all";
        }


    //DELETE PROJECT
    @PostMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable int projectId) {
            projectService.deleteProject(projectId);
            return "redirect:/projects/all";
    }
}
