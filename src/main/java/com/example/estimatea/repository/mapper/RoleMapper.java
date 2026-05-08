package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        role.setRoleType(rs.getString("role_type"));
        role.setRoleRate(rs.getInt("role_rate"));
        return role;
    }
}
