package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.SubProject;
import com.example.estimatea.repository.jdbc.SubProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class SubprojectServiceTest {

    @Mock
    private SubProjectRepository subProjectRepository;

    @InjectMocks
    private SubProjectService subProjectService;
    private SubProject subProjectMock;

    @BeforeEach()
    public void setUp() {
        subProjectMock = new SubProject(1, "Project calculation tool", LocalDate.of(2026, 2, 1), LocalDate.of(202, 3, 1), false, 1);
    }

    @Test
    void shouldReturnAllSubProjects() {
        when(subProjectRepository.getAllSubProjects()).thenReturn(List.of(subProjectMock));

        subProjectService.listAllSubProjects();

        verify(subProjectRepository).getAllSubProjects();
    }

            @Test
            void shouldHitNotFoundExceptionIfNoSubProjectsExists() {
                when(subProjectRepository.getAllSubProjects()).thenReturn(Collections.emptyList());
                assertThrows(NotFoundException.class, () -> subProjectService.listAllSubProjects());
            }

    @Test
    void shouldReturnSubProjectById() {
        when(subProjectRepository.findSubProjectById(1)).thenReturn(subProjectMock);

        subProjectService.findSubProjectById(1);

        verify(subProjectRepository).findSubProjectById(1);
    }

            @Test
            void shouldHitNotFoundExceptionIfProjectIdNull() { // "No subproject with given id exists " + subProjectId
                when(subProjectRepository.findSubProjectById(1)).thenReturn(null);
                assertThrows(NotFoundException.class, () -> subProjectService.findSubProjectById(1));
            }

    @Test
    void shouldReturnSubProjectsByProjectId() {
        when(subProjectRepository.findSubProjectsByProjectId(1)).thenReturn(List.of(subProjectMock));

        subProjectService.findSubProjectsByProjectId(1);

        verify(subProjectRepository).findSubProjectsByProjectId(1);
    }

            @Test
            void shouldHitNotFoundExceptionIfNoSubProjectsExistsByProjectId() { // "No subprojects exists for Main project " + projectId
                when(subProjectRepository.findSubProjectsByProjectId(1)).thenReturn(Collections.emptyList());
                assertThrows(NotFoundException.class, () -> subProjectService.findSubProjectsByProjectId(1));
            }
    @Test
    void shouldCreateSubProject() {
        when(subProjectRepository.createSubProject(subProjectMock)).thenReturn(1);

        subProjectService.createSubproject(subProjectMock);

        verify(subProjectRepository).createSubProject(subProjectMock);
    }

            @Test
            void shouldHitNotFoundExceptionForNullError() { // "No subproject object received"
                assertThrows(NotFoundException.class, () -> subProjectService.createSubproject(null));
            }

            @Test
            void shouldHitNotFoundExceptionForRowsAffectedError() { // "No subproject was created (ID)" + subProject
                when(subProjectRepository.createSubProject(subProjectMock)).thenReturn(0);
                assertThrows(NotFoundException.class, () -> subProjectService.createSubproject(subProjectMock));
            }

    @Test
    void shouldDeleteSubProject() {
        when(subProjectRepository.deleteSubProject(1)).thenReturn(1);

        subProjectService.DeleteSubproject(1);

        verify(subProjectRepository).deleteSubProject(1);
    }

            @Test
            void shouldHitNotFoundExceptionIfNoSubProjectWasDeleted() { // "No subproject was deleted (ID)" + subprojectId
                when(subProjectRepository.deleteSubProject(1)).thenReturn(0);
                assertThrows(NotFoundException.class, () -> subProjectService.DeleteSubproject(1));
            }

   @Test
    void shouldEditSubproject() {
        when(subProjectRepository.editSubProject(subProjectMock)).thenReturn(1);

        subProjectService.editSubProject(subProjectMock);

        verify(subProjectRepository).editSubProject(subProjectMock);
    }

            @Test
            void shouldHitNotFoundExceptionIfNoSubProjectWasUpdated() { // "No project was updated " + subProject.getProjectId()
                when(subProjectRepository.editSubProject(subProjectMock)).thenReturn(0);
                assertThrows(NotFoundException.class, () -> subProjectService.editSubProject(subProjectMock));
            }
}
