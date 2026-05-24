package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.mapper.SubProjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class SubProjectRepository {

    private final SubProjectMapper subMapper;
    private final JdbcTemplate jdbc;

        // SQL statements for subproject table

    private final String GET_ALL_SUBPROJECTS = "SELECT * FROM subproject ORDER BY project_id ASC";
    private final String GET_SUBPROJECT_BY_ID = "SELECT * FROM subproject WHERE sub_id = ?";
    private final String GET_SUBPROJECTS_BY_PROJECT = "SELECT * FROM subproject WHERE project_id = ?";
    private final String CREATE_SUBPROJECT = "INSERT INTO subproject (sub_name, start_date, deadline, sum_time, sum_price, completed, project_id) VALUES (?,?,?,?,?,?,?)";
    private final String DELETE_SUBPROJECT = "DELETE FROM subproject WHERE sub_id = ?";
    private final String UPDATE_SUBPROJECT = "UPDATE subproject SET sub_name = ?, start_date = ? ,deadline = ?, sum_time = ?, sum_price = ?, completed = ? WHERE sub_id = ?";
    private final String COMPLETE_SUBPROJECT = "UPDATE subproject SET completed = true WHERE sub_id = ?";

    private final String EDIT_DEADLINE = "UPDATE subproject SET deadline = ? WHERE sub_id = ?";

    public SubProjectRepository(SubProjectMapper subMapper, JdbcTemplate jdbc) {
        this.subMapper = subMapper;
        this.jdbc = jdbc;
    }

        // CRUD Query for subprojects

    // Returns all subprojects, sorted by projectId, ascending
    public List<SubProject> getAllSubProjects() {
        return jdbc.query(GET_ALL_SUBPROJECTS, subMapper);
    }

    // Returns individual subproject
    public SubProject findSubProjectById(int subProjectId) {
        return jdbc.queryForObject(GET_SUBPROJECT_BY_ID, subMapper, subProjectId);
    }

    // Returns list of subprojects attributed to a project
    public List<SubProject> findSubProjectsByProjectId(int projectId) {
        return jdbc.query(GET_SUBPROJECTS_BY_PROJECT, subMapper, projectId);
    }

    // Creates a new subproject with information from thymeleaf
    public int createSubProject(SubProject subProject) {
        return jdbc.update(CREATE_SUBPROJECT, subProject.getSubName(), subProject.getStartDate(),
                subProject.getDeadLine(), subProject.getSumTime(), subProject.getSumPrice(),
                subProject.isCompleted(), subProject.getProjectId());
    }

    // Deletes a subproject from the db
    public int deleteSubProject(int subProjectId) {
        return jdbc.update(DELETE_SUBPROJECT, subProjectId);
    }

    // UPDATE SUBPROJECT
    public int editSubProject(SubProject subProject) {
        return jdbc.update(UPDATE_SUBPROJECT, subProject.getSubName(), subProject.getStartDate(), subProject.getDeadLine(), subProject.getSumTime(), subProject.getSumPrice(),
                subProject.isCompleted(), subProject.getSubId());
    }

    // SUBPROJECT COMPLETE
    public int completeSubProject(int subProjectId) {
        return jdbc.update(COMPLETE_SUBPROJECT, subProjectId);
    }

//    //edits a subprojects deadline
//    public void editSubProjectDeadLine(LocalDate newDeadLine, int subProjectId) {
//        jdbc.update(EDIT_DEADLINE, newDeadLine, subProjectId);
//    }
//
//    //changes whether a subproject is set as completed
//    public void editSubProjectCompleted(boolean Completed, int subProjectId) {
//        jdbc.update(EDIT_COMPLETED, Completed, subProjectId);
//    }
}
