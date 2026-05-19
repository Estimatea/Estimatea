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

    //
    //Create task for Project
    @GetMapping("/{projectId}/add/project")
    public String addTaskToProjectForm(@PathVariable int projectId, Model model) {
        Project currentProject = projectService.findProjectById(projectId);
        model.addAttribute("project", currentProject);
        return "task-form";
    }

    @PostMapping("/createTaskForProject")
    public String createTaskForProject(@ModelAttribute Task task) {
        taskService.createTaskForProject(task);
        return "redirect:/project/" + task.getProjectId();
    }

    //
    //Create task for Subproject
    @GetMapping("/{subprojectId}/add/subproject")
    public String addTaskToSubprojectForm(@PathVariable int subprojectId, Model model) {
        SubProject currentSubproject = subProjectService.findSubProjectById(subprojectId);
        model.addAttribute("subproject", currentSubproject);
        return "task-form";
    }

    @PostMapping("/createTaskForSubproject")
    public String createTaskForSubproject(@ModelAttribute Task task) {
        taskService.createTaskForSubproject(task);
        return "redirect:/subproject/" + task.getSubprojectId();
    }

    //
    //Edit Task in Project
    @GetMapping("/{projectId}/{taskId}/edit")
    public String editTaskInProjectForm(@PathVariable int taskId, Model model) {
        Task currentTask = taskService.getTaskById(taskId);
        model.addAttribute("task", currentTask);
        return "task-form";
    }

    @PostMapping("/edit/project")
    public String editTaskInProject(@ModelAttribute Task task) {
        taskService.editTask(task);
        return "redirect:/project/" + task.getProjectId();
    }

    //Edit Task in Subproject
    @GetMapping("/{subprojectId}/{taskId}/edit")
    public String editTaskInSubprojectForm(@PathVariable int taskId, Model model) {
        Task currenTask = taskService.getTaskById(taskId);
        model.addAttribute("task", currenTask);
        return "task-form";
    }

    @PostMapping("/edit/subproject")
    public String editTaskInSubproject(@ModelAttribute Task task) {
        taskService.editTask(task);
        return "redirect:/subproject" + task.getSubprojectId();
    }

    //
    //Complete Task in Project
    @PostMapping("/complete/project")
    public String completeTaskInProject(@ModelAttribute Task task) {
        taskService.completeTask(task.getTaskId());
        return "redirect:/project/" + task.getProjectId();
    }

    //Complete Task in Subproject
    @PostMapping("/complete/subproject")
    public String completeTaskInSubproject(@ModelAttribute Task task) {
        taskService.completeTask(task.getTaskId());
        return "redirect:/subproject/" + task.getSubprojectId();
    }

    //
    //Delete Task in Project
    @PostMapping("/delete/project")
    public String deleteTaskInProject(@ModelAttribute Task task) {
        taskService.deleteTask(task.getTaskId());
        return "redirect:/project/" + task.getProjectId();
    }

    @PostMapping("/delete/subproject")
    public String deleteTask(@ModelAttribute Task task) {
        taskService.deleteTask(task.getTaskId());
        return "redirect:/subproject/" + task.getSubprojectId();
    }
}
