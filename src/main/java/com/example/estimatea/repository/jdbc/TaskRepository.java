package com.example.estimatea.repository.jdbc;

import com.example.estimatea.model.Task;
import com.example.estimatea.repository.mapper.TaskMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbc;
    private final TaskMapper taskMapper;

    // SQL statements for creating a single Task in both Projects and Subprojects
    private final String CREATE_TASK_FOR_PROJECT = "INSERT INTO task (task_name, task_time, task_price, task_complexity_id, start_date, deadline, project_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String CREATE_TASK_FOR_SUBPROJECT = "INSERT INTO task (task_name, task_time, task_price, task_complexity_id, start_date, deadline, project_id, subproject_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //SQL statement for editing a task in both Projects and Subprojects
    private final String EDIT_TASK = "UPDATE task SET task_name = ?, task_time = ?, task_price = ?, task_complexity_id = ?, start_date = ?, deadline = ?, project_id = ?, subproject_id = ? WHERE task_id = ? ";

    // SQL statement for deleting a single Task
    private final String DELETE_TASK = "DELETE FROM task WHERE task_id = ? ";

    //SQL statement for completing a single Task
    private final String COMPLETE_TASK = "UPDATE task SET completed = true WHERE task_id = ? ";

    // SQL statements for getting tasks in both Projects and Subprojects
    private final String GET_TASKS_BY_PROJECT_ID = "SELECT * FROM task WHERE project_id = ?";
    private final String GET_TASKS_BY_SUBPROJECT_ID = "SELECT * FROM task WHERE subproject_id = ?";

    public TaskRepository(JdbcTemplate jdbc, TaskMapper taskMapper) {
        this.jdbc = jdbc;
        this.taskMapper = taskMapper;
    }

    // Creating Task for both Projects and Subprojects
    public void createTaskForProject(Task task) {
        jdbc.update(CREATE_TASK_FOR_PROJECT, task.getTaskName(), task.getTaskTime(), task.getTaskPrice(), task.getComplexityId(), task.getStartDate(), task.getDeadLine() ,task.getProjectId());
    }

    public void createTaskForSubproject(Task task) {
        jdbc.update(CREATE_TASK_FOR_SUBPROJECT, task.getTaskName(), task.getTaskTime(), task.getTaskPrice(), task.getComplexityId(), task.getStartDate(), task.getDeadLine() ,task.getProjectId(),  task.getSubprojectId());
    }


    // Editing a single Task (works for both Project and Subproject)
    public void editTask(Task task) {
        jdbc.update(EDIT_TASK, task.getTaskName(), task.getTaskTime(), task.getTaskPrice(), task.getComplexityId(), task.getStartDate(), task.getDeadLine() ,task.getProjectId(),task.getSubprojectId(), task.getTaskId());
    }

    // Delete a single Task
    public void deleteTask(Task task) {
        jdbc.update(DELETE_TASK, task.getTaskId());
    }

    // Completing a single task (works for both a Project and Subproject)
    public void completeTask(Task task) {
        jdbc.update(COMPLETE_TASK, task.getTaskId());
    }


    // Get tasks within each Project and Subprojects
    public List<Task> getTasksByProjectId(int projectId) {
        return jdbc.query(GET_TASKS_BY_PROJECT_ID, taskMapper, projectId);
    }

    public List<Task> getTasksBySubprojectId(int subprojectId) {
        return jdbc.query(GET_TASKS_BY_SUBPROJECT_ID, taskMapper, subprojectId);
    }


}
