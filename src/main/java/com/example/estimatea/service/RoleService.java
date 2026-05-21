package com.example.estimatea.service;

import com.example.estimatea.exception.IllegalArgumentException;
import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Role;
import com.example.estimatea.repository.jdbc.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {

        List<Role> roles = roleRepository.getAllRoles();

        if (roles.isEmpty()) {
            throw new NotFoundException("No roles exists");
        }

        return roles;
    }

    public Role getRoleById(int id) {
        Role role = roleRepository.getRoleById(id);
        if (role == null) {
            throw new NotFoundException("No role exists");
        }
        return role;
    }

}
