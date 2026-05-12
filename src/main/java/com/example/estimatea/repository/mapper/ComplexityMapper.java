package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.Complexity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ComplexityMapper implements RowMapper<Complexity> {

    @Override
    public Complexity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Complexity complexityScore = new Complexity();
        complexityScore.setEstimateId(rs.getInt("estimates_id"));
        complexityScore.setTaskComplexity(rs.getInt("complexity_score"));
        return complexityScore;
    }
}
