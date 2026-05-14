package com.example.estimatea.repository;


import com.example.estimatea.model.Task;
import com.example.estimatea.repository.jdbc.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.net.URL;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile(){
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        System.out.println("URL= " + url);
    }

//    @BeforeEach
//    void setUp(){
//        // 1. Turn off constraint checks so we can clean everything
//        jdbc.execute("SET REFERENTIAL_INTEGRITY FALSE");
//
//        // 2. Truncate and reset IDs
//        jdbc.update("TRUNCATE TABLE task RESTART IDENTITY");
//        jdbc.update("TRUNCATE TABLE subproject RESTART IDENTITY");
//        jdbc.update("TRUNCATE TABLE project RESTART IDENTITY");
//        jdbc.update("TRUNCATE TABLE employee RESTART IDENTITY");
//        jdbc.update("TRUNCATE TABLE complexity RESTART IDENTITY");
//        jdbc.update("TRUNCATE TABLE role RESTART IDENTITY");
//
//        // 3. Turn checks back on
//        jdbc.execute("SET REFERENTIAL_INTEGRITY TRUE");
//
//        // 4. Insert fresh data
//        jdbc.update("INSERT INTO role (role_type, role_rate) VALUES (?, ?)", "Project Lead", 100);
//        jdbc.update("INSERT INTO employee (employee_name, employee_username, employee_password, role_id) VALUES (?, ?, ?, ?)", "Ole Olesen", "OleOle", "1234", 1);
//        jdbc.update("INSERT INTO project (project_name, start_date, completed, sum_time, sum_price, deadline, project_manager) VALUES (?, ?, ?, ?, ?, ?, ?)",
//                "Test Project", "2026-05-1", false, 0, 0, "2026-05-27", 1);
//        jdbc.update("INSERT INTO complexity (complexity_score, label_type, rate_multiplier) VALUES (?, ?, ?)", 1, "Standard", 1.0);
//        jdbc.update("INSERT INTO subproject (sub_name, start_date, deadline, completed, project_id) VALUES (?, ?, ?, ?, ?)",
//                "Test Subproject", "2026-01-01", "2026-12-31", false, 1);
//    }

    @Test
    void createTaskForProjectTest(){
        //Arrange
        Task task = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 0, 0, 1, 1, 1);

        //Act
        taskRepository.createTaskForProject(task);

        //Assert
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM task WHERE task_name = ?", Integer.class, "Test Task")).isEqualTo(1);
    }

    @Test
    void createTaskForSubprojectTest(){
        //Arrange
        Task task = new Task(LocalDate.of(2027, 1, 1), false, "Test Task", LocalDate.of(2027, 12, 1), 0, 0, 1, 1, 1);

        //Act
        taskRepository.createTaskForSubproject(task);

        //Assert
        assertThat(jdbc.queryForObject("SELECT COUNT(*) FROM task WHERE task_name = ? AND subproject_id = ?",
                Integer.class, "Test Task", 1)).isEqualTo(1);
    }


}
