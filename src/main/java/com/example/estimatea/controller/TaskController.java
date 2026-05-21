package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.service.ProjectService;
import com.example.estimatea.service.SubProjectService;
import org.springframework.ui.Model;
import com.example.estimatea.model.Task;
import com.example.estimatea.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    //Create task for Project
    @GetMapping("/project/{projectId}/add")
    public String createTaskForProjectForm(@PathVariable int projectId, Model model) {
        Project currentProject = projectService.findProjectById(projectId);
        model.addAttribute("project", currentProject);

        Task task = new Task();
        task.setProjectId(projectId);
        model.addAttribute("task", task);

        return "create-task-project";
    }

    @PostMapping("/createTaskForProject")
    public String createTaskForProject(@ModelAttribute Task task) {
        taskService.createTaskForProject(task);
        return "redirect:/projects/" + task.getProjectId();
    }

    //
    //Create task for Subproject
    @GetMapping("/subproject/{subprojectId}/add")
    public String createTaskToSubprojectForm(@PathVariable int subprojectId, Model model) {
        SubProject currentSubproject = subProjectService.findSubProjectById(subprojectId);
        model.addAttribute("subproject", currentSubproject);

        Task task = new Task();
        task.setSubprojectId(subprojectId);
        model.addAttribute("task", task);

        return "create-task-subproject";
    }

    @PostMapping("/createTaskForSubproject")
    public String createTaskForSubproject(@ModelAttribute Task task) {
        taskService.createTaskForSubproject(task);
        return "redirect:/subproject/" + task.getSubprojectId();
    }

    //
    //Edit Task in Project
    @GetMapping("/project/{projectId}/{taskId}/edit")
    public String editTaskInProjectForm(@PathVariable int taskId, Model model) {
        Task currentTask = taskService.getTaskById(taskId);
        model.addAttribute("task", currentTask);
        return "edit-task-project";
    }

    @PostMapping("/edit/project")
    public String editTaskInProject(@ModelAttribute("task") Task task) {
        taskService.editTask(task);
        return "redirect:/projects/" + task.getProjectId();
    }

    //Edit Task in Subproject
    @GetMapping("/subproject/{subprojectId}/{taskId}/edit")
    public String editTaskInSubprojectForm(@PathVariable int taskId, Model model) {
        Task currenTask = taskService.getTaskById(taskId);
        model.addAttribute("task", currenTask);
        return "edit-task-subproject";
    }

    @PostMapping("/edit/subproject")
    public String editTaskInSubproject(@ModelAttribute("task") Task task) {
        taskService.editTask(task);
        return "redirect:/subproject/" + task.getSubprojectId();
    }

    //
    //Complete Task in Project
    @PostMapping("/complete/project")
    public String completeTaskInProject(@ModelAttribute Task task) {
        taskService.completeTask(task.getTaskId());
        return "redirect:/projects/" + task.getProjectId();
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
        return "redirect:/projects/" + task.getProjectId();
    }

    @PostMapping("/delete/subproject")
    public String deleteTask(@ModelAttribute Task task) {
        taskService.deleteTask(task.getTaskId());
        return "redirect:/subproject/" + task.getSubprojectId();
    }
}
