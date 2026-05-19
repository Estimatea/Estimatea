package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.service.EmployeeService;
import com.example.estimatea.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeController.class)
@ActiveProfiles("test")
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;
    private Employee employeeMock;

    @BeforeEach
    void setUp() {
        employeeMock = new Employee(1, "Jackie", "jackie_dev", "password_777", 3);
    }

    @Test
    void controllerGetAllEmployeesTest() throws Exception { // GET
        when(employeeService.getAllEmployeeInCompany()).thenReturn(List.of(employeeMock));

        mockMvc.perform(get("/employee/all")).andExpect(status().isOk())
                                             .andExpect(view().name("employee-list"))
                                             .andExpect(model().attributeExists("employeeList"));

        verify(employeeService).getAllEmployeeInCompany();
    }

        // Employee handling Main Project

    // Adding employee to Main Project
    @Test
    void controllerAddEmployeeToProjectForm() throws Exception { // GET
        when(employeeService.getAllEmployeeInCompany()).thenReturn(List.of(employeeMock));

        mockMvc.perform(get("/employee/add/project")).andExpect(status().isOk())
                                                     .andExpect(view().name("add-employee-to-project"))
                                                     .andExpect(model().attributeExists("employeeList"));

        verify(employeeService).getAllEmployeeInCompany();
    }

            @Test
            void controllerEmployeeAddedToProject() throws Exception { // POST
                mockMvc.perform(post("/employee/add/project").param("employeeId", "1").param("projectId", "1"))
                                                             .andExpect(status().is3xxRedirection())
                                                             .andExpect(redirectedUrl("/project"));

                verify(employeeService).addEmployeeToProject(1, 1);
            }

    // Removing employee from Main Project
    @Test
    void controllerRemoveEmployeeFromProject() throws Exception {
        mockMvc.perform(post("/employee/project/remove").param("employeeId", "1").param("projectId", "1"))
                                                        .andExpect(status().is3xxRedirection())
                                                        .andExpect(redirectedUrl("/project"));

        verify(employeeService).removeEmployeeFromProject(1, 1);
    }

        // Employee handling Subproject




}
