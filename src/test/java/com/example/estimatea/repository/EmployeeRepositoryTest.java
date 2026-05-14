package com.example.estimatea.repository;
import com.example.estimatea.model.Employee;
import com.example.estimatea.repository.jdbc.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbc;

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
    void shouldGetAllProjectEmployeesById() {
        List<Integer> projectEmployees = employeeRepository.getAllEmployeeIdsForProject(1);

        assertThat(projectEmployees).isNotNull();
        assertThat(projectEmployees).hasSizeGreaterThan(0);
        assertThat(projectEmployees.size()).isEqualTo(3);
    }

    @Test
    void shouldShowAllEmployeesForGivenProject() {
        List<Employee> seededProjectEmployeeList = employeeRepository.getAllEmployeesForProject(1);

        assertThat(seededProjectEmployeeList).isNotNull();
        assertThat(seededProjectEmployeeList).hasSizeGreaterThan(0);
    }

    @Test
    void shouldAddEmployeeToProject() {
        int jackieId = 4; // Employee Nr. 4
        int projectId = 1;

        List<Integer> employeesBeforeAdding = employeeRepository.getAllEmployeeIdsForProject(projectId);
        assertThat(employeesBeforeAdding).doesNotContain(jackieId);

        int initialSize = employeesBeforeAdding.size();

        employeeRepository.addEmployeeToProject(jackieId, projectId); // Adds Jackie to ProjectId 1

        List<Integer> afterAddingNewEmployee = employeeRepository.getAllEmployeeIdsForProject(projectId);

        assertThat(afterAddingNewEmployee).hasSize(initialSize + 1);
        assertThat(afterAddingNewEmployee).contains(jackieId);
    }

    @Test
    void shouldRemoveEmployeeFromProject() {
        int joakimId = 1;
        int projectId = 1;

        List<Integer> employeesBeforeRemoval = employeeRepository.getAllEmployeeIdsForProject(projectId);
        assertThat(employeesBeforeRemoval).contains(joakimId);

        int initialSize = employeesBeforeRemoval.size();

        employeeRepository.removeEmployeeFromProject(joakimId, projectId);

        List<Integer> employeesAfterRemoval = employeeRepository.getAllEmployeeIdsForProject(projectId);

        assertThat(employeesAfterRemoval).hasSize(initialSize - 1);
        assertThat(employeesAfterRemoval).doesNotContain(joakimId);
    }

        // Sub-Project Employees
    @Test
    void shouldRetrieveAllEmployeesFromSubProjectById() {
        int subProjectId = 1;

        List<Integer> subProjectEmployeesById = employeeRepository.getAllEmployeesByIdForSubProject(subProjectId);

        assertThat(subProjectEmployeesById).isNotNull();
        assertThat(subProjectEmployeesById).hasSizeGreaterThan(0);
        assertThat(subProjectEmployeesById.size()).isEqualTo(2);
    }

    @Test
    void shouldRetrieveAllEmployeesFromSubProject() {
        int subProjectId = 1;

        List<Employee> subProjectEmployees = employeeRepository.getAllEmployeesForSubProject(subProjectId);

        assertThat(subProjectEmployees).isNotNull();
        assertThat(subProjectEmployees.size()).isGreaterThan(0);
    }

    @Test
    void shouldAssignEmployeeToSubProject() {
        int simonId = 3;
        int subProjectId = 1;

        List<Integer> employeesBeforeAdding = employeeRepository.getAllEmployeesByIdForSubProject(subProjectId);

        assertThat(employeesBeforeAdding).doesNotContain(simonId);

        int initialSize = employeesBeforeAdding.size();

        employeeRepository.assignEmployeeToSubProject(simonId, subProjectId); // Assigns Simon with EmployeeId 3

        List<Integer> afterAddingEmployeeToSubProject = employeeRepository.getAllEmployeesByIdForSubProject(subProjectId);

        assertThat(afterAddingEmployeeToSubProject).hasSize(initialSize + 1);
        assertThat(afterAddingEmployeeToSubProject).contains(simonId);
    }

    @Test
    void shouldRemoveEmployeeFromSubProject() {
        int joakimId = 1;
        int subProjectId = 1;

        List<Integer> employeesBeforeRemoval = employeeRepository.getAllEmployeesByIdForSubProject(subProjectId);

        assertThat(employeesBeforeRemoval).contains(joakimId);

        int initialSize = employeesBeforeRemoval.size();

        employeeRepository.removeEmployeeFromSubProject(joakimId,subProjectId); // Removes Joakim from subprojectId 1

        List<Integer> employeesAfterRemoval = employeeRepository.getAllEmployeesByIdForSubProject(subProjectId);

        assertThat(employeesAfterRemoval).hasSize(initialSize - 1);
        assertThat(employeesAfterRemoval).doesNotContain(joakimId);
    }
}
