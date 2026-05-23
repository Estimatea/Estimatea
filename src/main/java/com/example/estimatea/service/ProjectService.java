package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Project> listAllActiveProjects() {
        List<Project> updatedProjects = new ArrayList<>();

        for (Project p : projectRepository.getAllProjects()) {
            if (!p.isCompleted()) {
                updatedProjects.add(updateProjectScope(p));
            }
        }

        return updatedProjects;
    }

    public List<Project> listAllCompletedProjects() {
        List<Project> updatedProjects = new ArrayList<>();

        for (Project p : projectRepository.getAllProjects()) {
            if (p.isCompleted()) {
                updatedProjects.add(updateProjectScope(p));
            }
        }

        return updatedProjects;
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


        return updateProjectScope(project);
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

    // CHECKs both Project and Subproject tasks if completed
    public void completeProject(int projectId) {

        List<Task> projectTasks = taskService.getTasksByProjectId(projectId);
        for (Task mainTask : projectTasks) {

            if (!mainTask.getCompleted()) {
                throw new IllegalArgumentException("Cannot complete project: Main project task is still incomplete");
            }
        }

        List<SubProject> subProjects = subProjectService.findSubProjectsByProjectId(projectId);
            for (SubProject subProject : subProjects) {
                    List<Task> subProjectTaks = taskService.getTasksForSubprojectId(subProject.getSubId());

                for (Task subTask : subProjectTaks) {

                    if (!subTask.getCompleted()) {
                        throw new IllegalArgumentException("Cannot complete project: Subproject tasks is still incomplete");
                    }
                }
            }

            int rowsAffected = projectRepository.completeProject(projectId);

            if (rowsAffected == 0) {
                throw new NotFoundException("Project was not set to complete " + projectId);
            }
    }

    // PRICE AND TIME ESTIMATION FOR PROJECT

    public void updateProjectPriceAndTime(int projectId) {
        SubProject updatedSubProject;

        Project project = projectRepository.getProjectById(projectId);
        if (project == null) {
            throw new NotFoundException("No project with given ID found " + projectId);
        }
        project.setSumPrice(0);
        project.setSumTime(0);

        List<SubProject> subProjects = subProjectService.findSubProjectsByProjectId(projectId);
        for (SubProject s : subProjects) {
            updatedSubProject = subProjectService.updateSubProjectScope(s);
            project.setSumPrice(project.getSumPrice() + updatedSubProject.getSumPrice());
            project.setSumTime(project.getSumTime() + updatedSubProject.getSumTime());
        }

        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        List<Task> updatedTasks = new ArrayList<>();
        for (Task t : tasks) {
            updatedTasks.add(taskService.taskPriceCalculatorForTaskInProject(t));
        }

        for (Task t : updatedTasks) {
            project.setSumPrice(project.getSumPrice() + t.getTaskPrice());
            project.setSumTime(project.getSumTime() + t.getTaskTime());
        }

        editProject(project);
    }

    public Project updateProjectScope(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("No project object received");
        }
        updateProjectPriceAndTime(project.getProjectId());
        return projectRepository.getProjectById(project.getProjectId());
    }
}
