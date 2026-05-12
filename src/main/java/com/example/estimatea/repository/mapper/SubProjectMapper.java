package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.SubProject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubProjectMapper implements RowMapper<SubProject> {

    @Override
    public SubProject mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubProject subProject = new SubProject();
        subProject.setSubId(rs.getInt("sub_id"));
        subProject.setSubName(rs.getString("sub_name"));
        subProject.setStartDate(rs.getDate("start_date").toLocalDate());
        subProject.setDeadLine(rs.getDate("deadline").toLocalDate());
        subProject.setCompleted(rs.getBoolean("completed"));
        subProject.setProjectId(rs.getInt("project_id"));
        return subProject;
    }


}
