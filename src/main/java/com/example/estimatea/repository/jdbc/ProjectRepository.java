package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.Project;
import com.example.estimatea.repository.mapper.ProjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbc;
    private final ProjectMapper projectMapper;

        // SQL statements for Project table

    private final String CREATE_NEW_PROJECT = "INSERT INTO project (project_name, start_date, completed, sum_time, sum_price, deadline, project_manager) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_PROJECT_BY_ID = "SELECT * FROM project WHERE project_id = ?";
    private final String GET_ALL_PROJECTS = "SELECT * FROM project";
    private final String UPDATE_PROJECT = "UPDATE project SET project_name = ?, completed = ?, sum_time = ?, sum_price = ?, deadline = ?, project_manager = ? WHERE project_id = ?";
    private final String DELETE_PROJECT = "DELETE FROM project WHERE project_id = ?";

        // SORTING statements for Project Overview Page

    // {Deadline}
    private final String SHOW_ALL_PROJECTS_IN_ASC_ORDER = "SELECT * FROM project ORDER BY deadline ASC";
    private final String SHOW_ALL_PROJECTS_IN_DESC_ORDER = "SELECT * FROM project ORDER BY deadline DESC";

    // {start_date}
    private final String SORT_PROJECTS_BY_START_DATE_ASC = "SELECT * FROM project ORDER BY start_date ASC";
    private final String SORT_PROJECT_BY_START_DATE_DESC = "SELECT * FROM project ORDER BY start_date DESC";

    //SQL statement for completing PROJECT
    private final String COMPLETE_PROJECT = "UPDATE project SET completed = true WHERE project_id = ?";

    public ProjectRepository(JdbcTemplate jdbc, ProjectMapper projectMapper) {
        this.jdbc = jdbc;
        this.projectMapper = projectMapper;
    }

        // CRUD QUERY's for Project table

    public List<Project> getAllProjects() {
        return jdbc.query(GET_ALL_PROJECTS, projectMapper);
    }

    public int createNewProject(Project project) {
        return jdbc.update(CREATE_NEW_PROJECT,
                project.getProjectName(),
                project.getStartDate(),
                project.isCompleted(),
                project.getSumTime(),
                project.getSumPrice(),
                project.getDeadLine(),
                project.getProjectManager());
    }

    public Project getProjectById(int projectId) {
        return jdbc.queryForObject(FIND_PROJECT_BY_ID, projectMapper, projectId);
    }

    public int editProject(Project project) {
       return jdbc.update(UPDATE_PROJECT,
                project.getProjectName(),
                project.isCompleted(),
                project.getSumTime(),
                project.getSumPrice(),
                project.getDeadLine(),
                project.getProjectManager(),
                project.getProjectId());


    }

    public int deleteProject(int projectId) {
        return jdbc.update(DELETE_PROJECT, projectId);
    }

    // Completing a single task (works for both a Project and Subproject)
    public int completeProject(int projectId) {
        return jdbc.update(COMPLETE_PROJECT, projectId);
    }

        // CRUD QUREY's For SORTING
    public List<Project> sortProjectByAscendingOrder() {
        return jdbc.query(SHOW_ALL_PROJECTS_IN_ASC_ORDER, projectMapper); // ASCENDING
    }

    public List<Project> sortProjectByDescendingOrder() {
        return jdbc.query(SHOW_ALL_PROJECTS_IN_DESC_ORDER, projectMapper); // DESCENDING
    }

    public List<Project> sortByStartDateAscending() { // ASCENDING
        return jdbc.query(SORT_PROJECTS_BY_START_DATE_ASC, projectMapper);
    }

    public List<Project> sortByStartDateDescending() {  // DESCENDING
        return jdbc.query(SORT_PROJECT_BY_START_DATE_DESC, projectMapper);
    }
}
