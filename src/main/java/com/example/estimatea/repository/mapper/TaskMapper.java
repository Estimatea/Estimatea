package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setTaskId(rs.getInt("task_id"));
        task.setTaskName(rs.getString("task_name"));
        task.setTaskTime(rs.getInt("task_time"));
        task.setTaskPrice(rs.getInt("task_price"));
        task.setProjectId(rs.getInt("project_id"));
        task.setRessourceId(rs.getInt("ressource_id"));
        task.setStartDate(rs.getDate("start_date").toLocalDate());
        task.setCompleted(rs.getBoolean("completed"));
        return task;
    }
}
