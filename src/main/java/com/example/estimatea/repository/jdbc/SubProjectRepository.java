package com.example.estimatea.repository.jdbc;


import com.example.estimatea.model.Employee;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.mapper.SubProjectEmployeeMapper;
import com.example.estimatea.repository.mapper.SubProjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class SubProjectRepository {

    private final SubProjectMapper subMapper;
    private final JdbcTemplate jdbc;
    private final SubProjectEmployeeMapper subEmployeeMapper;

    //SQL statements for subproject table
    private final String GET_ALL_SUBPROJECTS = "SELECT * FROM sub_project ORDER BY project_id ASC";
    private final String GET_SUBPROJECT_BY_ID = "SELECT * FROM sub_project WHERE sub_id = ?";
    private final String GET_SUBPROJECT_BY_PROJECT = "SELECT * FROM sub_project WHERE project_id = ?";
    private final String CREATE_SUBPROJECT = "INSERT INTO sub_project(start_date, deadline, completed, sub_name, project_id) VALUES (?,?,?,?,?)";
    private final String DELETE_SUBPROJECT = "DELETE FROM sub_project WHERE sub_id = ?";
    private final String EDIT_DEADLINE = "UPDATE sub_project SET deadline = ? WHERE sub_id = ?";
    private final String EDIT_COMPLETED = "UPDATE sub_project SET completed = ? WHERE sub_id = ?";



    public SubProjectRepository(SubProjectMapper subMapper, JdbcTemplate jdbc, SubProjectEmployeeMapper subEmployeeMapper) {
        this.subMapper = subMapper;
        this.jdbc = jdbc;
        this.subEmployeeMapper = subEmployeeMapper;
    }

    //CRUD Query for subprojects
    //returns all subprojects, sorted by projectId, ascending
    public List<SubProject> getAllSubProjects() {
        return jdbc.query(GET_ALL_SUBPROJECTS, subMapper);
    }

    //returns individual subproject
    public SubProject getSubProjectById(int subProjectId) {
        return jdbc.queryForObject(GET_SUBPROJECT_BY_ID, subMapper, subProjectId);
    }

    //returns list of subprojects attributed to a project
    public List<SubProject> getSubProjectByProjectId(int projectId) {
        return jdbc.query(GET_SUBPROJECT_BY_PROJECT, subMapper, projectId);
    }

    //creates a new subproject with information from thymeleaf
    public void createSubProject(SubProject subProject) {
        jdbc.update(CREATE_SUBPROJECT, subProject.getStartDate(), subProject.getDeadLine(), subProject.isCompleted(), subProject.getSubName(), subProject.getProjectId());
    }

    //deletes a subproject from the db
    public void deleteSubProject(int subProjectId) {
        jdbc.update(DELETE_SUBPROJECT, subProjectId);
    }

    //edits a subprojects deadline
    public void editSubProjectDeadLine(LocalDate newDeadLine, int subProjectId) {
        jdbc.update(EDIT_DEADLINE, newDeadLine, subProjectId);
    }

    //changes whether a subproject is set as completed
    public void editSubProjectCompleted(Boolean Completed, int subProjectId) {
        jdbc.update(EDIT_COMPLETED, Completed, subProjectId);
    }

}
