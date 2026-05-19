package com.example.estimatea.controller;

import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.service.ProjectService;
import com.example.estimatea.service.SubProjectService;
import org.springframework.ui.Model;
import com.example.estimatea.model.Task;
import com.example.estimatea.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final SubProjectService subProjectService;

    public TaskController(TaskService taskService, ProjectService projectService, SubProjectService subProjectService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.subProjectService = subProjectService;
    }

    @GetMapping("/add/project")
    public String addTaskToProjectForm(@RequestParam int projectId, Model model) {
        Project currentProject = projectService.findProjectById(projectId);
        model.addAttribute("project", currentProject);
        return "task-form";

    }

            @PostMapping("/createTaskForProject")
            public String createTaskForProject(@ModelAttribute Task task) {
                taskService.creatTaskForProject(task);
                return "redirect:/project/";
            }



    @GetMapping("/add/subproject")
    public String addTaskToSubprojectForm(@RequestParam int subprojectId, Model model) {
        SubProject currentSubproejct = subProjectService.findSubProjectById(subprojectId);
        model.addAttribute("subproject", currentSubproejct);
        return "task-form";
    }

            @PostMapping("/createTaskForSubproejct")
            public String createTaskForSubproject(@ModelAttribute Task task) {
                taskService.createTaskForSubproject(task);
                return "redirect:/subproject";
            }


}
