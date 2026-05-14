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

        // SQL STATEMENTS FOR COMPLEXITY SCORES COUPLED TO TASK
    private final String UPDATE_CURRENT_COMPLEXITY_SCORE_ON_TASK = "UPDATE task SET current_complexity_id = ? WHERE task_id = ?";
//    private final String SHOW_ALL_CURRENT_COMPLEXITY_SCORES = "SELECT";

        // SQL STATEMENTS FOR TASK MANIPULATION
    private final String SHOW_ALL_TASKS = "SELECT * FROM task";

    // SQL statements for creating a single Task in both Projects and Subprojects
    private final String CREATE_TASK_FOR_PROJECT = "INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id,  employee_id, current_complexity_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String CREATE_TASK_FOR_SUBPROJECT = "INSERT INTO task (start_date, completed, task_name, deadline, task_time, task_price, project_id, subproject_id, employee_id, current_complexity_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //SQL statement for editing a task in both Projects and Subprojects
    private final String EDIT_TASK = "UPDATE task SET start_date = ?, completed = ?, task_name = ?, deadline = ?, task_time = ?, task_price = ?, employee_id = ?, current_complexity_id = ? WHERE task_id = ?";

    // SQL statement for deleting a single Task
    private final String DELETE_TASK = "DELETE FROM task WHERE task_id = ? ";

    //SQL statement for completing a single Task
    private final String COMPLETE_TASK = "UPDATE task SET completed = true WHERE task_id = ? ";

    //SQL statement for getting a single task by ID
    private final String GET_TASK_BY_ID = "SELECT * FROM task WHERE task_id= ?";

    // SQL statements for getting tasks in both Projects and Subprojects
    private final String GET_TASKS_BY_PROJECT_ID = "SELECT * FROM task WHERE project_id = ?";
    private final String GET_TASKS_BY_SUBPROJECT_ID = "SELECT * FROM task WHERE subproject_id = ?";

    public TaskRepository(JdbcTemplate jdbc, TaskMapper taskMapper) {
        this.jdbc = jdbc;
        this.taskMapper = taskMapper;
    }

    public List<Task> showAllTasks() {
        return jdbc.query(SHOW_ALL_TASKS, taskMapper);
    }

        // QUERY's to handle complexity_scores coupled to tasks
    public void updateComplexityScoreOnTask(int taskId) {
        jdbc.update(UPDATE_CURRENT_COMPLEXITY_SCORE_ON_TASK, taskId);
    }

    // Creating Task for both Projects and Subprojects ----- (EVT: Mulighed for at kombinere de to Create metoder til en metode der laver en boolean forespørgsel på id inden man trækker data)
    public void createTaskForProject(Task projectTask) {
        jdbc.update(CREATE_TASK_FOR_PROJECT, projectTask.getStartDate(), projectTask.getCompleted(), projectTask.getTaskName(), projectTask.getDeadLine(), projectTask.getTaskTime(), projectTask.getTaskPrice(), projectTask.getProjectId(), projectTask.getEmployeeId(), projectTask.getCurrentComplexityId());
    }

    public void createTaskForSubproject(Task subTask) {
        jdbc.update(CREATE_TASK_FOR_SUBPROJECT, subTask.getStartDate(), subTask.getCompleted(), subTask.getTaskName(), subTask.getDeadLine(), subTask.getTaskTime(), subTask.getTaskPrice(), subTask.getProjectId(), subTask.getSubprojectId(), subTask.getEmployeeId(), subTask.getCurrentComplexityId());
    }

    // Editing a single Task (works for both Project and Subproject)
    public void editTask(Task task) {
        jdbc.update(EDIT_TASK, task.getStartDate(), task.getCompleted(), task.getTaskName(), task.getDeadLine(), task.getTaskTime(), task.getTaskPrice(), task.getEmployeeId(), task.getCurrentComplexityId(), task.getTaskId());
    }

    // Delete a single Task
    public void deleteTask(Task task) {
        jdbc.update(DELETE_TASK, task.getTaskId());
    }

    // Completing a single task (works for both a Project and Subproject)
    public void completeTask(Task task) {
        jdbc.update(COMPLETE_TASK, task.getTaskId());
    }

    // Get a single task by ID
    public Task getTaskById(int taskId) {
        return jdbc.queryForObject(GET_TASK_BY_ID, taskMapper, taskId);
    }

    // Get tasks within each Project and Subprojects
    public List<Task> getTasksByProjectId(int projectId) {
        return jdbc.query(GET_TASKS_BY_PROJECT_ID, taskMapper, projectId);
    }

    public List<Task> getTasksBySubprojectId(int subprojectId) {
        return jdbc.query(GET_TASKS_BY_SUBPROJECT_ID, taskMapper, subprojectId);
    }


}
