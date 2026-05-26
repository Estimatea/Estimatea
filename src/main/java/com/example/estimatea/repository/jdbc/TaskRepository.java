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
    private final String CREATE_TASK_FOR_PROJECT = "INSERT INTO task (start_date, completed, task_name, deadline, task_time, project_id, current_complexity_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String CREATE_TASK_FOR_SUBPROJECT = "INSERT INTO task (start_date, completed, task_name, deadline, task_time, sub_id, current_complexity_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String CREATE_TASK_FOR_TEST = "INSERT INTO task (start_date, completed, task_name, deadline, task_time,project_id, sub_id,current_complexity_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //SQL statement for editing a task in both Projects and Subprojects
    private final String EDIT_TASK = "UPDATE task SET start_date = ?, completed = ?, task_name = ?, deadline = ?, task_time = ?, task_price = ?, current_complexity_id = ? WHERE task_id = ?";

    // SQL statement for deleting a single Task
    private final String DELETE_TASK = "DELETE FROM task WHERE task_id = ? ";

    //SQL statement for completing a single Task
    private final String COMPLETE_TASK = "UPDATE task SET completed = true WHERE task_id = ? ";

    //SQL statement for getting a single task by ID
    private final String GET_TASK_BY_ID = "SELECT * FROM task WHERE task_id= ?";

    //SQL statement for getting latest task
    private final String GET_LATEST_TASK = "SELECT * FROM task ORDER BY task_id DESC LIMIT 1";

    // SQL statements for getting tasks in both Projects and Subprojects
    private final String GET_TASKS_BY_PROJECT_ID = "SELECT * FROM task WHERE project_id = ?";
    private final String GET_TASKS_BY_SUB_ID = "SELECT * FROM task WHERE sub_id = ?";

    // SQL STATEMENTS FOR COMPLEXITY SCORES COUPLED TO TASK
    private final String UPDATE_CURRENT_COMPLEXITY_SCORE_ON_TASK = "UPDATE task SET current_complexity_id = ? WHERE task_id = ?";

    public TaskRepository(JdbcTemplate jdbc, TaskMapper taskMapper) {
        this.jdbc = jdbc;
        this.taskMapper = taskMapper;
    }

    // Creating Task for both Projects and Subprojects ----- (EVT: Mulighed for at kombinere de to Create metoder til en metode der laver en boolean forespørgsel på id inden man trækker data)
    public int createTaskForProject(Task projectTask) {
        return jdbc.update(CREATE_TASK_FOR_PROJECT,
                projectTask.getStartDate(),
                projectTask.getCompleted(),
                projectTask.getTaskName(),
                projectTask.getDeadLine(),
                projectTask.getTaskTime(),
                projectTask.getProjectId(),
                projectTask.getCurrentComplexityId());
    }

    public int createTaskForSubproject(Task subTask) {
        return jdbc.update(CREATE_TASK_FOR_SUBPROJECT,
                subTask.getStartDate(),
                subTask.getCompleted(),
                subTask.getTaskName(),
                subTask.getDeadLine(),
                subTask.getTaskTime(),
                subTask.getSubprojectId(),
                subTask.getCurrentComplexityId());
    }

    public int createTaskForTest(Task task) {
        return jdbc.update(CREATE_TASK_FOR_TEST,
                task.getStartDate(),
                task.getCompleted(),
                task.getTaskName(),
                task.getDeadLine(),
                task.getTaskTime(),
                task.getProjectId(),
                task.getSubprojectId(),
                task.getCurrentComplexityId());
    }

    // Editing a single Task (works for both Project and Subproject)
    public int editTask(Task task) {
        return jdbc.update(EDIT_TASK,
                task.getStartDate(),
                task.getCompleted(),
                task.getTaskName(),
                task.getDeadLine(),
                task.getTaskTime(),
                task.getTaskPrice(),
                task.getCurrentComplexityId(),
                task.getTaskId());
    }

    public Task getLatestTask() {
        return jdbc.queryForObject(GET_LATEST_TASK, new TaskMapper());
    }

    // Delete a single Task
    public int deleteTask(int taskId) {
        return jdbc.update(DELETE_TASK, taskId);
    }

    // Completing a single task (works for both a Project and Subproject)
    public int completeTask(int taskId) {
        return jdbc.update(COMPLETE_TASK, taskId);
    }

    // Get a single task by ID
    public Task getTaskById(int taskId) {
        return jdbc.queryForObject(GET_TASK_BY_ID, taskMapper, taskId);
    }

    // Get tasks within each Project and Subprojects
    //GET Tasks for a project by ID
    public List<Task> getTasksByProjectId(int projectId) {
        return jdbc.query(GET_TASKS_BY_PROJECT_ID, taskMapper, projectId);
    }

    //GET Tasks for a subproject by ID
    public List<Task> getTasksBySubprojectId(int subprojectId) {
        return jdbc.query(GET_TASKS_BY_SUB_ID, taskMapper, subprojectId);
    }

    // Update the complexity score on a single task
    public int updateComplexityScore(int complexityId, int taskId) {
        return jdbc.update(UPDATE_CURRENT_COMPLEXITY_SCORE_ON_TASK, complexityId, taskId);
    }


}
