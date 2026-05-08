package com.example.estimatea.repository.mapper;
import com.example.estimatea.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeName(rs.getString("employee_name"));
        employee.setEmployeeUsername(rs.getString("employee_username"));
        employee.setEmployeePassword(rs.getString("employee_password"));
        employee.setRoleId(rs.getInt("role_id"));
        return employee;
    }
}
