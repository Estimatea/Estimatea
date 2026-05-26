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

    private final String SHOW_ALL_COMPLEXITY_SCORES = "SELECT * FROM complexity";
    private final String SHOW_COMPLEXITY_SCORE_BY_ID = "SELECT complexity_id, complexity_score, label_type, rate_multiplier FROM complexity WHERE complexity_id = ?";

    public ComplexityRepository(JdbcTemplate jdbc, ComplexityMapper complexityMapper) {
        this.jdbc = jdbc;
        this.complexityMapper = complexityMapper;
    }

        // Query's for complexity table

    public List<Complexity> showAllComplexityScores() {
        return jdbc.query(SHOW_ALL_COMPLEXITY_SCORES, complexityMapper);
    }

    public Complexity showComplexityScoreById(int currentComplexityId) {
        return jdbc.queryForObject(SHOW_COMPLEXITY_SCORE_BY_ID, complexityMapper, currentComplexityId);
    }
}
