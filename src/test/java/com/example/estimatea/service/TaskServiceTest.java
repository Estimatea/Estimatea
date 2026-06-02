package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Complexity;
import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ComplexityService complexityService;

    @InjectMocks
    private TaskService taskService;

    private Task taskMock;

    private Complexity complexityMock;

    @BeforeEach
    public void setUp() {
        complexityMock = new Complexity(1, 4, "TestComp", 1.2);
        taskMock = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 10, 1, 1, 1);
    }


    //Unit test on createTaskForProject
    //
    @Test
    void createTaskForProject_shouldCreateTask() {

        when(employeeService.getAllEmployeesByProjectId(1))
                .thenReturn(List.of());

        when(complexityService.getComplexityFromId(anyInt()))
                .thenReturn(complexityMock);

        when(taskRepository.createTaskForProject(any(Task.class)))
                .thenReturn(1);

        when(taskRepository.getLatestTask())
                .thenReturn(taskMock);

        taskService.createTaskForProject(taskMock);

        verify(taskRepository).createTaskForProject(any(Task.class));
    }
            @Test
            void createTaskForProject_shouldThrowWhenTaskIsNull() {

                assertThrows(NotFoundException.class, () -> taskService.createTaskForProject(null));
            }

                    @Test
                    void createTaskForProject_shouldThrowWhenNoRowsAffected() {

                        when(taskRepository.createTaskForProject(taskMock)).thenReturn(0);

                        assertThrows(NotFoundException.class, () -> taskService.createTaskForProject(taskMock));
                    }


    //Unit test on createTaskForSubproject
    //
    @Test
    void createTaskForSubproject_shouldCreateTask() {

        when(complexityService.getComplexityFromId(anyInt()))
                .thenReturn(complexityMock);

        when(taskRepository.createTaskForSubproject(any(Task.class)))
                .thenReturn(1);

        when(taskRepository.getLatestTask())
                .thenReturn(taskMock);

        taskService.createTaskForSubproject(taskMock);

        verify(taskRepository).createTaskForSubproject(any(Task.class));
    }

            @Test
            void createTaskForSubproject_shouldThrowWhenTaskIsNull() {

                assertThrows(NotFoundException.class, () -> taskService.createTaskForSubproject(null));
            }

                    @Test
                    void createTaskForSubproject_shouldThrowWhenNoRowsAffected() {

                        when(taskRepository.createTaskForSubproject(taskMock)).thenReturn(0);

                        assertThrows(NotFoundException.class, () -> taskService.createTaskForSubproject(taskMock));
                    }


    //Unit test on editTask
    //
    @Test
    void editTask_shouldEditTask() {

        when(taskRepository.editTask(taskMock)).thenReturn(1);

        assertDoesNotThrow(() -> taskService.editTask(taskMock));
    }

            @Test
            void editTask_shouldThrowWhenTaskIsNull() {

                assertThrows(IllegalArgumentException.class, () -> taskService.editTask(null));
            }

                    @Test
                    void editTask_shouldThrowWhenNoRowsAffected() {
                        when(taskRepository.editTask(taskMock)).thenReturn(0);

                        assertThrows(NotFoundException.class, () -> taskService.editTask(taskMock));
                    }


    //Unit test on deleteTask
    //
    @Test
    void deleteTask_shouldDeleteTask() {

        when(taskRepository.deleteTask(1)).thenReturn(1);

        assertDoesNotThrow(() -> taskService.deleteTask(1));
    }

            @Test
            void deleteTask_shouldThrowWhenNoRowsAffected() {

                when(taskRepository.deleteTask(1)).thenReturn(0);

                assertThrows(NotFoundException.class, () -> taskService.deleteTask(1));
            }


    //Unit test on completeTask
    //
    @Test
    void completeTask_shouldCompleteTask() {

        when(taskRepository.completeTask(1)).thenReturn(1);

        assertDoesNotThrow(() -> taskService.completeTask(1));
    }

            @Test
            void completeTask_shouldThrowWhenNoRowsAffected() {

                when(taskRepository.completeTask(1)).thenReturn(0);

                assertThrows(NotFoundException.class, () -> taskService.completeTask(1));
            }


    //Unit test on getTaskById
    //
    @Test
    void getTaskById_ShouldGetTaskById() {

        when(taskRepository.getTaskById(1)).thenReturn(taskMock);

        Task result = taskService.getTaskById(1);

        assertEquals(taskMock, result);
    }

            @Test
            void getTaskById_shouldThrowWhenNotFound() {

                when(taskRepository.getTaskById(1)).thenThrow(org.springframework.dao.EmptyResultDataAccessException.class);
                ;

                assertThrows(NotFoundException.class, () -> taskService.getTaskById(1));
            }


    //Unit test on getTasksByProjectId
    //
    @Test
    void getTasksByProjectId_shouldGetTaskByProjectId() {

        when(taskRepository.getTasksByProjectId(1)).thenReturn(List.of(taskMock));

        List<Task> result = taskService.getTasksByProjectId(1);

        assertEquals(1, result.size());
        assertEquals(taskMock, result.getFirst());
    }


    //Unit test on getTasksBySubprojectId
    //
    @Test
    void getTasksBySubprojectId_shouldGetTasksBySubprojectId() {

        when(taskRepository.getTasksBySubprojectId(1)).thenReturn(List.of(taskMock));

        List<Task> result = taskService.getTasksForSubprojectId(1);

        assertEquals(1, result.size());
        assertEquals(taskMock, result.getFirst());
    }



    //Unit test on updateComplexityScore
    //
    @Test
    void updateComplexityScore_shouldUpdateComplexityScore() {

        when(taskRepository.updateComplexityScore(2, 1)).thenReturn(1);

        assertDoesNotThrow(() -> taskService.updateComplexityScore(2, 1));
    }

            @Test
            void updateComlexityScore_shouldThrowWhenNoRowsAffected() {

                when(taskRepository.updateComplexityScore(2, 1)).thenReturn(0);

                assertThrows(NotFoundException.class, () -> taskService.updateComplexityScore(2, 1));
            }
}
