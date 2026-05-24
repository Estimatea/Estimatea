package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Role;
import com.example.estimatea.service.EmployeeService;
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
        employeeMock = new Employee(1, "Jackie", "jackie_dev", "password_777", new Role("Test Role", 200));
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
        when(employeeService.employeesNotInProject(1)).thenReturn(List.of(employeeMock));

        mockMvc.perform(get("/employee/add/1").param("projectId", "1")).andExpect(status().isOk())
                                                     .andExpect(view().name("add-employee-to-project"))
                                                     .andExpect(model().attributeExists("employeeList"));

        verify(employeeService).employeesNotInProject(1);
    }

            @Test
            void controllerEmployeeAddedToProject() throws Exception { // POST
                mockMvc.perform(post("/employee/add/1").param("employeeId", "1").param("projectId", "1"))
                                                             .andExpect(status().is3xxRedirection())
                                                             .andExpect(redirectedUrl("/projects/1"));

                verify(employeeService).addEmployeeToProject(1, 1);
            }

    // Removing employee from Main Project
    @Test
    void controllerRemoveEmployeeFromProject() throws Exception {
        mockMvc.perform(post("/employee/remove/1").param("employeeId", "1").param("projectId", "1"))
                                                        .andExpect(status().is3xxRedirection())
                                                        .andExpect(redirectedUrl("/projects/1"));

        verify(employeeService).removeEmployeeFromProject(1, 1);
    }

        // Employee handling Subproject

    @Test
    void controllerAddEmployeeToSubProjectForm() throws Exception {
        when(employeeService.getAllEmployeeViableToAddToSubProject(1,1)).thenReturn(List.of(employeeMock));

        mockMvc.perform(get("/employee/add/subproject")
                        .param("projectId", "1")
                        .param("subProjectId", "1"))
                .andExpect(status().isOk());

        verify(employeeService).getAllEmployeeViableToAddToSubProject(1,1);
    }

            @Test
            void controllerEmployeeAddedToSubProject() throws Exception {
                mockMvc.perform(post("/employee/add/subproject")
                                .param("employeeId", "1")
                                .param("subProjectId", "1")
                                .param("projectId", "1"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/subproject/1"));

                verify(employeeService).addEmployeeToSubProject(1, 1, 1);
            }


    @Test
    void controllerRemoveEmployeeFromSubProject() throws Exception {
        mockMvc.perform(post("/employee/remove/subproject").param("employeeId", "1").param("subProjectId", "1").param("projectId", "1"))
                                                           .andExpect(status().is3xxRedirection())
                                                           .andExpect(redirectedUrl("/subproject/1"));

        verify(employeeService).removeEmployeeFromSubProject(1, 1);
    }
}
