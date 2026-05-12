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

    // SORTING statements for Project Overview Page
//    private final String SHOW_ALL_PROJECTS = "SELECT * FROM project ORDER BY deadline DESC";


    public ProjectRepository(JdbcTemplate jdbc, ProjectMapper projectMapper) {
        this.jdbc = jdbc;
        this.projectMapper = projectMapper;
    }

    // CRUD QUERY's for Project table


}
