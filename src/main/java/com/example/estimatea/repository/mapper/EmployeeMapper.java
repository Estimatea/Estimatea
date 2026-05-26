package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.Employee;
import com.example.estimatea.repository.jdbc.RoleRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeMapper implements RowMapper<Employee> {

    private final RoleRepository roleRepository;

    // Constructor injection — the recommended approach
    public EmployeeMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setEmployeeName(rs.getString("employee_name"));
        employee.setEmployeeUsername(rs.getString("employee_username"));
        employee.setEmployeePassword(rs.getString("employee_password"));
        employee.setRole(roleRepository.getRoleById(rs.getInt("role_id")));
        return employee;
    }
}
