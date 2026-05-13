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
    private final String SHOW_COMPLEXITY_SCORE_BY_ID = "SELECT complexity_id, complexity_score, label_type, rate_multiplier FROM complexity WHERE complexity_id = ?";
    private final String CREATE_COMPLEXITY_SCORE = "INSERT INTO complexity (complexity_score, label_type, rate_multiplier) VALUES (?, ?, ?)";
    private final String DELETE_COMPLEXITY_SCORE_FROM_TASK = "DELETE FROM task WHERE task_id = ? AND current_complexity_id = ?";
    private final String UPDATE_CURRENT_COMPLEXITY_SCORE_ON_TASK = "UPDATE task SET current_complexity_id = ? WHERE task_id = ?";

    // SQL sorting statements for complexity in (GANTT Diagram)

    public ComplexityRepository(JdbcTemplate jdbc, ComplexityMapper complexityMapper) {
        this.jdbc = jdbc;
        this.complexityMapper = complexityMapper;
    }

    // CRUD Query's for complexity table
    public Complexity showComplexityScoreById(int currentComplexityId) {
        return jdbc.queryForObject(SHOW_COMPLEXITY_SCORE_BY_ID, complexityMapper, currentComplexityId);
    }

    public void createComplexityScore(Complexity complexity) {
        jdbc.update(CREATE_COMPLEXITY_SCORE, complexity.getComplexityScore(), complexity.getLabelType(), complexity.getRateMultiplier());
    }

    public void deleteComplexityScore(int taskId, int currentComplexityId) {
        jdbc.update(DELETE_COMPLEXITY_SCORE_FROM_TASK, taskId, currentComplexityId);
    }

    public void updateComplexityScoreOnTask(int taskId) {
        jdbc.update(UPDATE_CURRENT_COMPLEXITY_SCORE_ON_TASK, taskId);
    }
}
