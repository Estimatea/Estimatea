package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;
    private final RoleService roleService;
    private final ComplexityService complexityService;

    public TaskService(TaskRepository taskRepository, EmployeeService employeeService, RoleService roleService,  ComplexityService complexityService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.complexityService = complexityService;
    }

    public void createTaskForTest(Task projectTask) {

        if (projectTask == null) {
            throw new NotFoundException("No task object received");
        }

        int rowsAffected = taskRepository.createTaskForProject(projectTask);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was created " + projectTask.getTaskId());
        }

        taskPriceCalculatorForTaskInProject(projectTask);

    }

    public void createTaskForSubproject(Task subTask) {

        if (subTask == null) {
            throw new NotFoundException("No task object received");
        }

        int rowsAffected = taskRepository.createTaskForSubproject(subTask);

        if (rowsAffected == 0) {
            throw new NotFoundException("No task was created " + subTask.getTaskId());
        }

        taskPriceCalculatorForTaskInSubProject(subTask);

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
            return Collections.emptyList();
        }

        return tasks;
    }

    public void updateComplexityScore(int complexityId, int taskId) {
        int complexityScore = taskRepository.updateComplexityScore(complexityId, taskId);

        if (complexityScore == 0) {
            throw new NotFoundException("No task found with ID: " + taskId);
        }
    }


    // Calculates the price for the Task, which cascades through the entire program
    public Task taskPriceCalculatorForTaskInProject(Task task) {
        double price = 0;

        List <Employee> empList = employeeService.getAllEmployeesByProjectId(task.getProjectId());

        for (Employee e : empList) {
            double rate = e.getRole().getRoleRate();
            price += ( rate * task.getTaskTime() ) / empList.size();
        }
        double multiplier = complexityService.getComplexityFromId(task.getCurrentComplexityId()).getRateMultiplier();

        task.setTaskPrice((int) Math.round(price * multiplier));
        taskRepository.editTask(task);

        return taskRepository.getTaskById(task.getTaskId());
    }

    public Task taskPriceCalculatorForTaskInSubProject(Task task) {
        double price = 0;

        List <Employee> empList = employeeService.getAllEmployeesForSubproject(task.getSubprojectId());
        for (Employee e : empList) {
            double rate = e.getRole().getRoleRate();
            price += ( rate * task.getTaskTime() ) / empList.size();
        }
        double multiplier = complexityService.getComplexityFromId(task.getCurrentComplexityId()).getRateMultiplier();

        task.setTaskPrice((int) Math.round(price * multiplier));
        taskRepository.editTask(task);

        return taskRepository.getTaskById(task.getTaskId());
    }


}
