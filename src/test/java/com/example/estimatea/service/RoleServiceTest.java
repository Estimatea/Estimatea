package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Role;
import com.example.estimatea.repository.jdbc.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;
    private Role roleMock;

    @BeforeEach
    public void setUp() {
        roleMock = new Role(1, "Developer", 450);
    }

    //Unit test on getAllRoles
    //
    @Test
    void getAllRoles_shouldGetAllRoles() {

        when(roleRepository.getAllRoles()).thenReturn(List.of(roleMock));

        List<Role> result = roleService.getAllRoles();

        assertEquals(1, result.size());
        assertEquals(roleMock, result.getFirst());
        assertEquals("Developer", result.getFirst().getRoleType());
    }

    @Test
    void getAllRoles_shouldThrowWhenEmpty() {

        when(roleRepository.getAllRoles()).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> roleService.getAllRoles());
    }

}
