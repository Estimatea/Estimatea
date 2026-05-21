package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.ProjectRepository;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final SubProjectRepository subProjectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, SubProjectRepository subProjectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.subProjectRepository = subProjectRepository;
    }

    public void createTaskForProject(Task projectTask) {

        if (projectTask == null) {
            throw new NotFoundException("No task object received");
        }

        int rowsAffected = taskRepository.createTaskForProject(projectTask);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was created " + projectTask.getTaskId());
        }

    }

    public void createTaskForSubproject(Task subTask) {

        if (subTask == null) {
            throw new NotFoundException("No task object received");
        }

        int rowsAffected = taskRepository.createTaskForSubproject(subTask);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was created " + subTask.getTaskId());
        }


    }

    public void editTask(Task task) {

        if (task == null) {
            throw new IllegalArgumentException("No task object received");
        }

        int rowsAffected = taskRepository.editTask(task);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was updated " + task.getTaskId());
        }
    }

    public void deleteTask(int taskId) {

        int rowsAffected = taskRepository.deleteTask(taskId);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was deleted " + taskId);
        }
    }

    public void completeTask(int taskId) {

        int rowsAffected = taskRepository.completeTask(taskId);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was completed " + taskId);
        }
    }

    public Task getTaskById(int taskId) {
        try {
            return taskRepository.getTaskById(taskId);

        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            throw new NotFoundException("No task with given ID found " + taskId);
        }
    }

    public List<Task> getTasksByProjectId(int projectId) {
        return taskRepository.getTasksByProjectId(projectId);
    }

    public List<Task> getTasksForSubprojectId(int subprojectId) {

        List<Task> tasks = taskRepository.getTasksBySubprojectId(subprojectId);

        if (tasks.isEmpty()) {
            throw new NotFoundException("No tasks exists for subproject: " + subprojectId);
        }

        return tasks;
    }

    public void updateComplexityScore(int complexityId, int taskId) {
        int complexityScore = taskRepository.updateComplexityScore(complexityId, taskId);

        if (complexityScore == 0) {
            throw new NotFoundException("No task found with ID: " + taskId);
        }
    }


}
