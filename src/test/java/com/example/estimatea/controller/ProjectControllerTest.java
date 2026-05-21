package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.model.Task;
import com.example.estimatea.service.EmployeeService;
import com.example.estimatea.service.ProjectService;
import com.example.estimatea.service.SubProjectService;
import com.example.estimatea.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@ActiveProfiles("test")
public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;
    private Project projectMock;

    @MockitoBean
    private SubProjectService subProjectService;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private TaskService taskService;


    @BeforeEach
    void setUp() {
        projectMock = new Project(LocalDate.of(2027, 1, 1), false, "Test Project", 0, 0, LocalDate.of(2027, 12, 31), 1);
    }
    
    @Test
    void controllerGetAllProjectForm() throws Exception { // GET
        when(projectService.listAllActiveProjects()).thenReturn(List.of(projectMock));

        mockMvc.perform(get("/projects/all")).andExpect(status().isOk())
                                             .andExpect(view().name("all-projects"))
                                             .andExpect(model().attributeExists("projectList"));

        verify(projectService).listAllActiveProjects();
    }

    // Project Overview
    @Test
    void controllerGetProjectOverview() throws Exception { // GET
        Employee employeeMock = new Employee();
        SubProject subprojectMock = new SubProject();
        Task taskMock = new Task();

        when(projectService.findProjectById(1)).thenReturn(projectMock);
        when(employeeService.getAllEmployeesByProjectId(1)).thenReturn(List.of(employeeMock));
        when(subProjectService.findSubProjectsByProjectId(1)).thenReturn(List.of(subprojectMock));
        when(taskService.getTasksByProjectId(1)).thenReturn(List.of(taskMock));

        mockMvc.perform(get("/projects/1").param("projectId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("view-project"))
                .andExpect(model().attributeExists("project"))
                .andExpect(model().attributeExists("employeeList"))
                .andExpect(model().attributeExists("subProjectList"))
                .andExpect(model().attributeExists("taskList"));

        verify(projectService).findProjectById(1);
        verify(employeeService).getAllEmployeesByProjectId(1);
        verify(subProjectService).findSubProjectsByProjectId(1);
        verify(taskService).getTasksByProjectId(1);
    }

    // UPDATE/EDIT PROJECT
    @Test
    void controllerEditProjectForm() throws Exception { // GET
        when(projectService.findProjectById(1)).thenReturn(projectMock);

         mockMvc.perform(get("/projects/1/edit").param("projectId", "1"))
                                                 .andExpect(status().isOk())
                                                 .andExpect(view().name("edit-project"))
                                                 .andExpect(model().attributeExists("project"));

         verify(projectService).findProjectById(1);
    }


            @Test
            void controllerSaveProjectChanges() throws Exception { // POST
                mockMvc.perform(post("/projects/1/edit/save").param("projectId", "1"))
                                                             .andExpect(status().is3xxRedirection())
                                                             .andExpect(redirectedUrl("/projects/1"));

                verify(projectService).editProject(any(Project.class));
            }

    // CREATE NEW PROJECT
    @Test
    void controllerCreateProjectForm() throws Exception { // GET
        mockMvc.perform(get("/projects/create")).andExpect(status().isOk())
                                                .andExpect(view().name("create-project"));
    }

            @Test
            void controllerSaveNewProject() throws Exception { // POST
                mockMvc.perform(post("/projects/create/save")).andExpect(status().is3xxRedirection())
                                                              .andExpect(redirectedUrl("/projects/all"));

                verify(projectService).createNewProject(any(Project.class));
            }

    // DELETE PROJECT
    @Test
    void controllerDeleteProject() throws Exception {
        mockMvc.perform(post("/projects/1/delete")).andExpect(status().is3xxRedirection())
                                                   .andExpect(redirectedUrl("/projects/all"));

        verify(projectService).deleteProject(1);
    }
}



    
    
