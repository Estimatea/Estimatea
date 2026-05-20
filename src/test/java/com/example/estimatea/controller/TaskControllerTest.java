package com.example.estimatea.controller;

import com.example.estimatea.model.Project;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.model.Task;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@ActiveProfiles("test")
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;

    @MockitoBean
    private SubProjectService subProjectService;

    @MockitoBean
    private TaskService taskService;

    private Task taskMock;

    @BeforeEach
    void setUp() {
        taskMock = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 100, 1, 0, 2, 1);
    }

    // Add Task to Project Form
    @Test
    void controllerCreateTaskToProjectForm() throws Exception {
        when(projectService.findProjectById(1)).thenReturn(new Project());

        mockMvc.perform(get("/project/task/1/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-form"))
                .andExpect(model().attributeExists("project"));

        verify(projectService).findProjectById(1);
    }

            // Create Task for Project
            @Test
            void controllerCreateTaskForProject() throws Exception {
                mockMvc.perform(post("/task/createTaskForProject")
                                .param("taskId", "2")
                                .param("projectId", "1"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/projects/1"));

                verify(taskService).createTaskForProject(any(Task.class));
            }

    // Add Task to Subproject Form
    @Test
    void controllerAddTaskToSubprojectForm() throws Exception {
        when(subProjectService.findSubProjectById(1)).thenReturn(new SubProject());

        mockMvc.perform(get("/subproject/task/1/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-form"))
                .andExpect(model().attributeExists("subproject"));

        verify(subProjectService).findSubProjectById(1);
    }

            // Create Task for Subproject
            @Test
            void controllerCreateTaskForSubproject() throws Exception {
                mockMvc.perform(post("/task/createTaskForSubproject")
                                .param("taskId", "2")
                                .param("subprojectId", "1"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/subproject/1"));

                verify(taskService).createTaskForSubproject(any(Task.class));
            }

    // Edit Task in Project Form
    @Test
    void controllerEditTaskInProjectForm() throws Exception {
        when(taskService.getTaskById(2)).thenReturn(taskMock);

        mockMvc.perform(get("/project/task/1/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-form"))
                .andExpect(model().attributeExists("task"));

        verify(taskService).getTaskById(2);
    }

            // Edit Task in Project
            @Test
            void controllerEditTaskInProject() throws Exception {
                mockMvc.perform(post("/task/edit/project")
                                .param("taskId", "2")
                                .param("projectId", "1"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/projects/1"));

                verify(taskService).editTask(any(Task.class));
            }

    // Edit Task in Subproject Form
    @Test
    void controllerEditTaskInSubprojectForm() throws Exception {
        when(taskService.getTaskById(2)).thenReturn(taskMock);

        mockMvc.perform(get("/subproject/task/1/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-form"))
                .andExpect(model().attributeExists("task"));

        verify(taskService).getTaskById(2);
    }

            // Edit Task in Subproject
            @Test
            void controllerEditTaskInSubproject() throws Exception {
                mockMvc.perform(post("/task/edit/subproject")
                                .param("taskId", "2")
                                .param("subprojectId", "1"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/subproject/1"));

                verify(taskService).editTask(any(Task.class));
            }

    // Complete Task in Project
    @Test
    void controllerCompleteTaskInProject() throws Exception {
        mockMvc.perform(post("/task/complete/project")
                        .param("taskId", "2")
                        .param("projectId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects/1"));

        verify(taskService).completeTask(2);
    }

    // Complete Task in Subproject
    @Test
    void controllerCompleteTaskInSubproject() throws Exception {
        mockMvc.perform(post("/task/complete/subproject")
                        .param("taskId", "2")
                        .param("subprojectId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subproject/1"));

        verify(taskService).completeTask(2);
    }

    // Delete Task in Project
    @Test
    void controllerDeleteTaskInProject() throws Exception {
        mockMvc.perform(post("/task/delete/project")
                        .param("taskId", "2")
                        .param("projectId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects/1"));

        verify(taskService).deleteTask(2);
    }

    // Delete Task in Subproject
    @Test
    void controllerDeleteTaskInSubproject() throws Exception {
        mockMvc.perform(post("/task/delete/subproject")
                        .param("taskId", "2")
                        .param("subprojectId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subproject/1"));

        verify(taskService).deleteTask(2);
    }
}