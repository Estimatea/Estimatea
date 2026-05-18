package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Project;
import com.example.estimatea.repository.jdbc.ProjectRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;
    private Project projectMock;

    @BeforeEach()
    public void setUp() {
        projectMock = new Project(1, LocalDate.of(2026,1,1), false, "Alpha Solutions", 120, 1500, LocalDate.of(2026,12,31), 1);
    }

    @Test
    void shouldReturnAllProjects() {
        when(projectRepository.getAllProjects()).thenReturn(List.of(projectMock));

        List<Project> currentProjects = projectService.listAllProjects();

        assertNotNull(currentProjects);
        assertThat(currentProjects.size() == 1);
        assertThat(currentProjects.getFirst()).isEqualTo(projectMock);
    }
            @Test
            void shouldHitNotFoundExceptionIfEmpty() {
                when(projectRepository.getAllProjects()).thenReturn(Collections.emptyList());
                assertThrows(NotFoundException.class, () -> projectService.listAllProjects());
            }

    @Test
    void shouldReturnProjectById() {
        when(projectRepository.getProjectById(1)).thenReturn(projectMock);

        projectService.findProjectById(1);

        assertNotNull(projectMock);

        verify(projectRepository).getProjectById(1);
    }

            @Test
            void shouldHitNotFoundExceptionForProjectById() {
                assertThrows(NotFoundException.class, () -> projectService.findProjectById(1));
            }

    @Test
    void shouldCreateNewProject() {
        when(projectRepository.createNewProject(projectMock)).thenReturn(1);

        projectService.createNewProject(projectMock);

        verify(projectRepository).createNewProject(projectMock);
    }

            @Test
            void shouldHitNotFoundExceptionForNullError() { // "No project object received"
                assertThrows(NotFoundException.class, () -> projectService.createNewProject(null));
            }

            @Test
            void shouldHitNotFoundExceptionForRowsAffectedError() { // "No project with given ID found " + projectId
                when(projectRepository.createNewProject(projectMock)).thenReturn(0);
                assertThrows(NotFoundException.class, () -> projectService.createNewProject(projectMock));
            }

    @Test
    void shouldEditProject() {
        when(projectRepository.editProject(projectMock)).thenReturn(1);

        projectService.editProject(projectMock);

        verify(projectRepository).editProject(projectMock);
    }

            @Test
            void shouldHitIllegalArgumentExceptionError() { // "No project object received"
                assertThrows(IllegalArgumentException.class, () -> projectService.editProject(null));
            }

            @Test
            void shouldHitNotFoundExceptionError() { // "No project was updated " + project.getProjectId()
                when(projectRepository.editProject(projectMock)).thenReturn(0);
                assertThrows(NotFoundException.class, () -> projectService.editProject(projectMock));
            }

    @Test
    void shouldDeleteProject() {
        when(projectRepository.deleteProject(1)).thenReturn(1);

        projectService.deleteProject(1);

        verify(projectRepository).deleteProject(1);
    }

            @Test
            void shouldHitNotFoundExceptionWhenDeletingError() { // "No project was deleted " + projectId
                when(projectRepository.deleteProject(1)).thenReturn(0);
                assertThrows(NotFoundException.class, () -> projectService.deleteProject(1));
            }
}
