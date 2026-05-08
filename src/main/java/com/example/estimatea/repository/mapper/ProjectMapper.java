package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.Project;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setProjectName(rs.getString("project_name"));
        project.setSumTime(rs.getInt("sum_time"));
        project.setSumPrice(rs.getInt("sum_price"));
        project.setDeadLine(rs.getDate("deadline").toLocalDate());
        project.setProjectManager(rs.getInt("project_manager"));
        return project;
    }
}
