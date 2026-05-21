package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final SubProjectService subProjectService;
    private final TaskService taskService;

    public ProjectService(ProjectRepository projectRepository, SubProjectService subProjectService, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.subProjectService = subProjectService;
        this.taskService = taskService;
    }

    public List<Project> listAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();

        if (projects.isEmpty()) {
            throw new NotFoundException("No projects exists");
        }

//        for (Project project : projects) {
//            updateProjectScope(project);
//        }
        return projects;
    }

    public void createNewProject(Project project) {
        if (project == null) {
            throw new NotFoundException("No project object received");
        }

        int rowsAffected = projectRepository.createNewProject(project);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was created " + project.getProjectId());
        }
    }

    public Project findProjectById(int projectId) {
        Project project = projectRepository.getProjectById(projectId);

        if (project == null) {
            throw new NotFoundException("No project with given ID found " + projectId);
        }

//        updateProjectScope(project);
        return project;
    }

    public void editProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("No project object received");
        }

        int rowsAffected = projectRepository.editProject(project);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was updated " + project.getProjectId());
        }
    }

    public void deleteProject(int projectId) {
        int rowsAffected = projectRepository.deleteProject(projectId);

        if (rowsAffected == 0) {
            throw new NotFoundException("No project was deleted " + projectId);
        }
    }

    // PRICE AND TIME ESTIMATION FOR PROJECT

//    public void updateProjectScope(Project project) {
//        if (project == null) {
//            throw new IllegalArgumentException("No project object received");
//        }
//        updateProjectPrice(project.getProjectId());
//        updateProjectTime(project.getProjectId());
//    }
//
//    public void updateProjectPrice(int projectId) {
//        if (findProjectById(projectId) == null) {
//            throw new NotFoundException("No project with given ID found " + projectId);
//        }
//        Project project = projectRepository.getProjectById(projectId);
//        project.setSumPrice(0);
//
//        List<SubProject> subProjects = subProjectService.findSubProjectsByProjectId(projectId);
//        if (!subProjects.isEmpty()) {
//            for (SubProject s : subProjects) {
//                subProjectService.updateSubProjectScope(s.getSubId());
//                project.setSumPrice(project.getSumPrice() + s.getSumPrice());
//            }
//        }
//
//        List<Task> tasks = taskService.getTasksByProjectId(projectId);
//        if (!tasks.isEmpty()) {
//            for (Task t : tasks) {
//                project.setSumPrice(project.getSumPrice() + t.getTaskPrice());
//            }
//        }
//
//        editProject(project);
//
//    }
//
//    public void updateProjectTime(int projectId) {
//        if (findProjectById(projectId) == null) {
//            throw new NotFoundException("No project with given ID found " + projectId);
//        }
//        Project project = projectRepository.getProjectById(projectId);
//        project.setSumTime(0);
//
//        List<SubProject> subProjects = subProjectService.findSubProjectsByProjectId(projectId);
//        if (!subProjects.isEmpty()) {
//            for (SubProject s : subProjects) {
//                subProjectService.updateSubProjectScope(s.getSubId());
//                project.setSumTime(project.getSumTime() + s.getSumTime());
//            }
//        }
//
//        List<Task> tasks = taskService.getTasksByProjectId(projectId);
//        if (!tasks.isEmpty()) {
//            for (Task t : tasks) {
//                project.setSumTime(project.getSumTime() + t.getTaskTime());
//            }
//        }
//
//        editProject(project);
//
//    }
    public void updateProjectScope(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("No project object received");
        }
        updateProjectPrice(project.getProjectId());
        updateProjectTime(project.getProjectId());
    }

    public void updateProjectPrice(int projectId) {
        Project project = projectRepository.getProjectById(projectId);
        if (project == null) {
            throw new NotFoundException("No project with given ID found " + projectId);
        }
        project.setSumPrice(0);

        List<SubProject> subProjects = subProjectService.findSubProjectsByProjectId(projectId);
        if (!subProjects.isEmpty()) {
            for (SubProject s : subProjects) {
                subProjectService.updateSubProjectScope(s);
                project.setSumPrice(project.getSumPrice() + s.getSumPrice());
            }
        }

        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        if (!tasks.isEmpty()) {
            for (Task t : tasks) {
                project.setSumPrice(project.getSumPrice() + t.getTaskPrice());
            }
        }

        editProject(project);

    }

    public void updateProjectTime(int projectId) {
        Project project = projectRepository.getProjectById(projectId);
        if (project == null) {
            throw new NotFoundException("No project with given ID found " + projectId);
        }
        project.setSumTime(0);

        List<SubProject> subProjects = subProjectService.findSubProjectsByProjectId(projectId);
        if (!subProjects.isEmpty()) {
            for (SubProject s : subProjects) {
                subProjectService.updateSubProjectScope(s);
                project.setSumTime(project.getSumTime() + s.getSumTime());
            }
        }

        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        if (!tasks.isEmpty()) {
            for (Task t : tasks) {
                project.setSumTime(project.getSumTime() + t.getTaskTime());
            }
        }

        editProject(project);

    }
}
