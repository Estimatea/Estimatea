package com.example.estimatea.service;

import com.example.estimatea.exception.NotFoundException;
import com.example.estimatea.model.Employee;
import com.example.estimatea.model.Role;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;
    private Employee employeeMock;

    @BeforeEach()
    public void setUp() {
        employeeMock = new Employee(1, "Jackie", "jackie_dev", "password_777", new Role("Test Role", 200));
    }

    @Test
    void shouldReturnAllEmployeesInCompany() {
        when(employeeRepository.getAllEmployeesInCompany()).thenReturn(List.of(employeeMock));

        List<Employee> employeeList = employeeService.getAllEmployeeInCompany();

        assertNotNull(employeeList);
        assertThat(employeeList.size() == 1);
        assertThat(employeeList.getFirst().getEmployeeName()).isEqualTo("Jackie");
        assertThat(employeeList.getFirst().getEmployeeUsername()).isEqualTo("jackie_dev");
        assertThat(employeeList.getFirst().getEmployeePassword()).isEqualTo("password_777");


        verify(employeeRepository).getAllEmployeesInCompany();
    }

        // Employee tests on Main project

    @Test
    void shouldReturnAllEmployeesByProjectId() {
        when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(List.of(employeeMock));

        List<Employee> employeeList = employeeService.getAllEmployeesByProjectId(1);

        assertNotNull(employeeList);
        assertThat(employeeList.size() == 1);
        assertThat(employeeList.getFirst().getEmployeeName()).isEqualTo("Jackie");
        assertThat(employeeList.getFirst().getEmployeeUsername()).isEqualTo("jackie_dev");
        assertThat(employeeList.getFirst().getEmployeePassword()).isEqualTo("password_777");


        verify(employeeRepository).getAllEmployeesForProject(1);
    }

    @Test
    void shouldAddEmployeeToProject() {
        when(employeeRepository.addEmployeeToProject(1,1)).thenReturn(1);

        employeeService.addEmployeeToProject(1, 1);

        verify(employeeRepository).addEmployeeToProject(1, 1);
    }

    @Test
    void shouldRemoveEmployeeFromProject() {
        when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(List.of(employeeMock));
        when(employeeRepository.removeEmployeeFromProject(1, 1)).thenReturn(1);

        employeeService.removeEmployeeFromProject(1, 1);

        verify(employeeRepository).removeEmployeeFromProject(1, 1);
    }
            @Test
            void shouldHitIllegalArgumentExceptionWhenRemoving() { // "No employee with given id exists on project " + projectId
                when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(Collections.emptyList());

                assertThrows(IllegalArgumentException.class, () -> employeeService.removeEmployeeFromProject(1, 1));
            }

            @Test
            void shouldHitNotFoundExceptionWhenRemoving() { // "Employee not found " + projectId + " EMP: " + employeeId
                when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(List.of(employeeMock));
                when(employeeRepository.removeEmployeeFromProject(1, 1)).thenReturn(0);

                assertThrows(NotFoundException.class, () -> employeeService.removeEmployeeFromProject(1, 1));
            }

        // Sub_project_employees test on SUBPROJECTS

    @Test
    void shouldReturnAllEmployeesFromSubProjectById() {
        when(employeeRepository.getAllEmployeesForSubProject(1)).thenReturn(List.of(employeeMock));

        List<Employee> subprojectEmployees = employeeService.getAllEmployeesForSubproject(1);

        assertNotNull(subprojectEmployees);
        assertThat(subprojectEmployees.size() == 1);
        assertThat(subprojectEmployees.getFirst().getEmployeeName()).isEqualTo("Jackie");
        assertThat(subprojectEmployees.getFirst().getEmployeeUsername()).isEqualTo("jackie_dev");
        assertThat(subprojectEmployees.getFirst().getEmployeePassword()).isEqualTo("password_777");

        verify(employeeRepository).getAllEmployeesForSubProject(1);
    }

    @Test
    void shouldAddEmployeeToSubproject() {
        when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(List.of(employeeMock));
        when(employeeRepository.assignEmployeeToSubProject(1, 1)).thenReturn(1);

        employeeService.addEmployeeToSubProject(1, 1, 1);

        verify(employeeRepository).assignEmployeeToSubProject(1, 1);
    }
            @Test
            void shouldHitIllegalArgumentExceptionWhenAddingToSubproject() { // "Invalid assignment - employee not found on MAIN project " + projectId
                when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(Collections.emptyList());

                assertThrows(IllegalArgumentException.class, () -> employeeService.addEmployeeToSubProject(1, 1, 1));
            }

            @Test
            void shouldHitNotFoundExceptionWhenAddingToSubProject() { // "Employee not found " + projectId + " EMP: " + employeeId)
                when(employeeRepository.getAllEmployeesForProject(1)).thenReturn(List.of(employeeMock));
                when(employeeRepository.assignEmployeeToSubProject(1, 1)).thenReturn(0);

                assertThrows(NotFoundException.class, () -> employeeService.addEmployeeToSubProject(1, 1, 1));
            }

    @Test
    void shouldRemoveEmployeeFromSubProject() {
        when(employeeRepository.getAllEmployeesForSubProject(1)).thenReturn(List.of(employeeMock));
        when(employeeRepository.removeEmployeeFromSubProject(1, 1)).thenReturn(1);

        employeeService.removeEmployeeFromSubProject(1, 1);

        verify(employeeRepository).removeEmployeeFromSubProject(1, 1);
    }

            @Test
            void shouldHitIllegalArgumentExceptionWhenRemovingFromSubproject() { // "Invalid removal - employee not to be found on Subproject with ID: " + subProjectId
                when(employeeRepository.getAllEmployeesForSubProject(1)).thenReturn(Collections.emptyList());

                assertThrows(IllegalArgumentException.class, () -> employeeService.removeEmployeeFromSubProject(1, 1));
            }

            @Test
            void shouldHitNotFoundExceptionWhenRemovingFromSubProject() { // "Employee not found " + subProjectId + " EMP: " + employeeId
                when(employeeRepository.getAllEmployeesForSubProject(1)).thenReturn(List.of(employeeMock));
                when(employeeRepository.removeEmployeeFromSubProject(1, 1)).thenReturn(0);

                assertThrows(NotFoundException.class, () -> employeeService.removeEmployeeFromSubProject(1, 1));
            }
}
