package com.example.estimatea.repository.jdbc;
import com.example.estimatea.model.Role;
import com.example.estimatea.repository.mapper.RoleMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    private final JdbcTemplate jdbc;
    private final RoleMapper roleMapper;

    private final String GET_ALL_ROLES = "SELECT * FROM role";
//    private final String GET_ROLE_BY_ID = "SELECT * FROM role WHERE role_id = ?";

    public RoleRepository(JdbcTemplate jdbc,  RoleMapper roleMapper) {
        this.jdbc = jdbc;
        this.roleMapper = roleMapper;
    }

    // Gets all roles
    public List<Role> getAllRoles() {
        return jdbc.query(GET_ALL_ROLES, roleMapper);
    }
//
//    // Returns specific role
//    public Role getRoleById(int roleId) {
//        return jdbc.queryForObject(GET_ROLE_BY_ID, roleMapper, roleId);
//    }
}
