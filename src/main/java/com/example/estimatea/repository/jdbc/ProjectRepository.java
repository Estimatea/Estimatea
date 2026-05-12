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
    private final String SHOW_PROJECT_OVERVIEW = "SELECT * FROM project";

    // SORTING statements for Project Overview Page
    private final String SHOW_ALL_PROJECTS = "SELECT * FROM project ORDER BY deadline DESC";


    public ProjectRepository(JdbcTemplate jdbc, ProjectMapper projectMapper) {
        this.jdbc = jdbc;
        this.projectMapper = projectMapper;
    }

    // CRUD QUERY's for Project table
    public List<Project> showProjectOverview() {
        return jdbc.query(SHOW_PROJECT_OVERVIEW, projectMapper);
    }
}
