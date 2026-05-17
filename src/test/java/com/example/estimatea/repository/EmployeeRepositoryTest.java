package com.example.estimatea.repository;
import com.example.estimatea.model.Employee;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import java.net.URL;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        assertNotNull(url);
    }

        // Company employee list
    @Test
    void shouldRetrieveAllEmployeesFromCompany() {
        List<Employee> allEmployees = employeeRepository.getAllEmployeesInCompany();

        assertThat(allEmployees).isNotNull();
        assertThat(allEmployees).hasSizeGreaterThan(0);
    }

        // Project Employees
    @Test
    void shouldShowAllEmployeesForGivenProject() {
        int projectId = 1; // Alpha Solutions

        List<Employee> seededProjectEmployeeList = employeeRepository.getAllEmployeesForProject(projectId);

        assertThat(seededProjectEmployeeList).isNotNull();
        assertThat(seededProjectEmployeeList).hasSizeGreaterThan(0);
    }

    @Test
    void shouldAddEmployeeToProject() {
        int testEmployeeId = 4;  // Employee Nr. 4 Jackie
        int projectId = 1; // Alpha Solutions

        List<Employee> employeesBeforeAdding = employeeRepository.getAllEmployeesForProject(projectId);
        assertThat(employeesBeforeAdding).extracting(Employee::getEmployeeId).doesNotContain(testEmployeeId);

        int initialSize = employeesBeforeAdding.size();

        employeeRepository.addEmployeeToProject(testEmployeeId, projectId); // Adds TestEmployee to ProjectId 1

        List<Employee> afterAddingNewEmployee = employeeRepository.getAllEmployeesForProject(projectId);

        assertThat(afterAddingNewEmployee).hasSize(initialSize + 1);
        assertThat(afterAddingNewEmployee).extracting(Employee::getEmployeeId).contains(testEmployeeId);
    }

    @Test
    void shouldRemoveEmployeeFromProject() {
        int testEmployee = 1; // Employee Nr. 1 Joakim
        int projectId = 1; // Alpha Solutions

        List<Employee> employeesBeforeRemoval = employeeRepository.getAllEmployeesForProject(projectId);
        assertThat(employeesBeforeRemoval).extracting(Employee::getEmployeeId).contains(testEmployee);

        int initialSize = employeesBeforeRemoval.size();

        employeeRepository.removeEmployeeFromProject(testEmployee, projectId);

        List<Employee> employeesAfterRemoval = employeeRepository.getAllEmployeesForProject(projectId);

        assertThat(employeesAfterRemoval).hasSize(initialSize - 1);
        assertThat(employeesAfterRemoval).extracting(Employee::getEmployeeId).doesNotContain(testEmployee);
    }

        // Sub-Project Employees
    @Test
    void shouldRetrieveAllEmployeesFromSubProject() {
        int subProjectId = 1; // Project calculation tool

        List<Employee> subProjectEmployees = employeeRepository.getAllEmployeesForSubProject(subProjectId);

        assertThat(subProjectEmployees).isNotNull();
        assertThat(subProjectEmployees.size()).isGreaterThan(0);
    }

    @Test
    void shouldAssignEmployeeToSubProject() {
        int testEmployeeId = 3; // Employee Nr.3 Simon
        int subProjectId = 1; // Project calculation tool

        List<Employee> employeesBeforeAdding = employeeRepository.getAllEmployeesForSubProject(subProjectId);

        assertThat(employeesBeforeAdding).extracting(Employee::getEmployeeId).doesNotContain(testEmployeeId);

        int initialSize = employeesBeforeAdding.size();

        employeeRepository.assignEmployeeToSubProject(testEmployeeId, subProjectId); // Assigns Simon with EmployeeId 3

        List<Employee> afterAddingEmployeeToSubProject = employeeRepository.getAllEmployeesForSubProject(subProjectId);

        assertThat(afterAddingEmployeeToSubProject).hasSize(initialSize + 1);
        assertThat(afterAddingEmployeeToSubProject).extracting(Employee::getEmployeeId).contains(testEmployeeId);
    }

    @Test
    void shouldRemoveEmployeeFromSubProject() {
        int testEmployee = 1; // Employee nr. 1 Joakim
        int subProjectId = 1; // Project calculation tool

        List<Employee> employeesBeforeRemoval = employeeRepository.getAllEmployeesForSubProject(subProjectId);

        assertThat(employeesBeforeRemoval).extracting(Employee::getEmployeeId).contains(testEmployee);

        int initialSize = employeesBeforeRemoval.size();

        employeeRepository.removeEmployeeFromSubProject(testEmployee,subProjectId); // Removes Joakim from subprojectId 1

        List<Employee> employeesAfterRemoval = employeeRepository.getAllEmployeesForSubProject(subProjectId);

        assertThat(employeesAfterRemoval).hasSize(initialSize - 1);
        assertThat(employeesAfterRemoval).extracting(Employee::getEmployeeId).doesNotContain(testEmployee);
    }
}

//    @Test
//    void shouldGetAllProjectEmployeesById() {
//        int projectId = 1;
//
//        List<Integer> projectEmployees = employeeRepository.getAllEmployeeIdsForProject(projectId);
//
//        assertThat(projectEmployees).isNotNull();
//        assertThat(projectEmployees).hasSizeGreaterThan(0);
//        assertThat(projectEmployees.size()).isEqualTo(3);
//    }

//    void shouldRetrieveAllEmployeesIdsFromSubProject() {
//        int subProjectId = 1;
//
//        List<Integer> subProjectEmployeesById = employeeRepository.getAllEmployeesIdsForSubProject(subProjectId);
//
//        assertThat(subProjectEmployeesById).isNotNull();
//        assertThat(subProjectEmployeesById).hasSizeGreaterThan(0);
//        assertThat(subProjectEmployeesById.size()).isEqualTo(2);
//    }