package com.example.estimatea.repository.jdbc;

import com.example.estimatea.repository.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeRepository {

    private final JdbcTemplate jdbc;
    private final EmployeeMapper employeeMapper;

    public EmployeeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.employeeMapper = employeeMapper;
    }
}
