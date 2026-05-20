package com.example.estimatea.controller;

import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.service.EmployeeService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SubProjectController.class)
@ActiveProfiles("test")
public class SubProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private SubProjectService subProjectService;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private TaskService taskService;


    private Employee employeeMock;
    private SubProject subProjectMock;
    private Project projectMock;

    @BeforeEach
    public void setup() {
        employeeMock = new Employee(1, "Jonathan Test", "jonathantestemand", "112testmodtaget", 1);
        projectMock = new Project(1, LocalDate.now(), false, "Test Project", 1000, 1000000, LocalDate.now().plusYears(2), 1);
        subProjectMock = new SubProject(1, "Test SubProject", LocalDate.now(), LocalDate.now().plusYears(1), false, 1);
    }

    @Test
    void controllerGetSpecificSubProjectTest() throws Exception { // GET
        when(subProjectService.findSubProjectById(subProjectMock.getSubId())).thenReturn(subProjectMock);
        when(employeeService.getAllEmployeesForSubproject(subProjectMock.getSubId())).thenReturn(List.of());
        when(taskService.getTasksForSubprojectId(subProjectMock.getSubId())).thenReturn(List.of());

        mockMvc.perform(get("/subproject/{subProjectId}", subProjectMock.getSubId())).andExpect(status().isOk())
                                                    .andExpect(view().name("view-subproject"))
                                                    .andExpect(model().attribute("subProject", subProjectMock));

        verify(subProjectService).findSubProjectById(subProjectMock.getSubId());
    }

    @Test
    void controllerCreateSubProjectTest() throws Exception { // GET
        when(subProjectService.findSubProjectsByProjectId(projectMock.getProjectId())).thenReturn(List.of(subProjectMock));

        mockMvc.perform(get("/subproject/create"))
                                        .andExpect(status().isOk())
                                        .andExpect(view().name("create-subproject"))
                                        .andExpect(model().attributeExists("subProject"));

    }

    @Test
    void controllerSaveCreatedSubProjectTest() throws Exception {
        mockMvc.perform(post("/subproject/create/save")
                        .flashAttr("subProject", subProjectMock))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects/" + subProjectMock.getProjectId()));

        verify(subProjectService).createSubproject(subProjectMock);
    }

    @Test
    void controllerEditSubProjectTest() throws Exception {
        when(subProjectService.findSubProjectById(subProjectMock.getSubId())).thenReturn(subProjectMock);

        mockMvc.perform(get("/subproject/{subProjectId}/edit", subProjectMock.getSubId()))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-subproject"))
                .andExpect(model().attribute("subProject", subProjectMock));

        verify(subProjectService).findSubProjectById(subProjectMock.getSubId());
    }

    @Test
    void controllerSaveSubProjectChangesTest() throws Exception {
        mockMvc.perform(post("/subproject/save")
                        .flashAttr("subproject", subProjectMock))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subproject/" + subProjectMock.getSubId()));

        verify(subProjectService).editSubProject(subProjectMock);
    }

    @Test
    void controllerDeleteSubProjectTest() throws Exception {
        when(subProjectService.findSubProjectById(subProjectMock.getSubId())).thenReturn(subProjectMock);

        mockMvc.perform(post("/subproject/{subprojectId}/delete", subProjectMock.getSubId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects/" + subProjectMock.getProjectId()));

        verify(subProjectService).findSubProjectById(subProjectMock.getSubId());
        verify(subProjectService).DeleteSubproject(subProjectMock.getSubId());
    }



}