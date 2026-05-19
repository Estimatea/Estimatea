package com.example.estimatea.controller;


import com.example.estimatea.exception.IllegalArgumentException;
import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Project;
import com.example.estimatea.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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
                return "redirect:/projects/{projectId}";
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
    @PostMapping
    public String deleteProject(@ModelAttribute("project") Project project, Model model) {
        try {
            projectService.deleteProject(project.getProjectId());
            return "redirect:/projects/all";
        } catch (NotFoundException e) {
            model.addAttribute("error", e);
            return "error";
        }
    }
}
