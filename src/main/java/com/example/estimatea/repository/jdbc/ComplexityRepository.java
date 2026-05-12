package com.example.estimatea.repository.jdbc;

import com.example.estimatea.model.Complexity;
import com.example.estimatea.repository.mapper.ComplexityMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplexityRepository {

    private final JdbcTemplate jdbc;
    private final ComplexityMapper complexityMapper;

    // SQL statements for complexity table Links to task
    private final String SHOW_COMPLEXITY_SCORE_ON_GIVEN_TASK = "SELECT * FROM complexity WHERE task_id = ?";
    private final String CREATE_COMPLEXITY_SCORE = "INSERT INTO complexity (complexity_score) VALUES (?)";
    private final String DELETE_COMPLEXITY_SCORE_FROM_TASK = "DELETE FROM complexity WHERE estimate_id = ? AND task_id = ?";
    private final String UPDATE_COMPLEXITY_SCORE_ON_TASK = "UPDATE complexity SET complexity_score = ? WHERE task_id = ?";

    // SQL sorting statements for complexity in (GANTT Diagram)


    public ComplexityRepository(JdbcTemplate jdbc, ComplexityMapper complexityMapper) {
        this.jdbc = jdbc;
        this.complexityMapper = complexityMapper;
    }

    // CRUD Query's for complexity table
    public List<Complexity> showComplexityScores(Complexity complexity) {
        return jdbc.query(SHOW_COMPLEXITY_SCORE_ON_GIVEN_TASK, complexityMapper, complexity.getEstimateId(), complexity.getTaskComplexity(), complexity.getTaskId());
    }

    public void createComplexityScore(Complexity complexity) {
        jdbc.update(CREATE_COMPLEXITY_SCORE, complexityMapper, complexity.getEstimateId(), complexity.getTaskComplexity(), complexity.getTaskId());
    }

    public void deleteComplexityScore(Complexity complexity) {
        jdbc.update(DELETE_COMPLEXITY_SCORE_FROM_TASK, complexity.getTaskId(), complexity.getEstimateId());
    }

    public void updateComplexityScoreOnTask(Complexity complexity) {
        jdbc.update(UPDATE_COMPLEXITY_SCORE_ON_TASK, complexityMapper, complexity.getEstimateId(), complexity.getTaskId());
    }
}
