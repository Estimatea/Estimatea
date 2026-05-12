package com.example.estimatea.repository.jdbc;

import com.example.estimatea.repository.mapper.ComplexityMapper;
import com.example.estimatea.repository.mapper.TaskMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TaskRepository {

    private JdbcTemplate jdbc;
    private TaskMapper taskMapper;


}
