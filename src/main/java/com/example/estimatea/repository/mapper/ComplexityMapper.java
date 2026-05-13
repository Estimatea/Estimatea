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
        complexityScore.setComplexityId(rs.getInt("complexity_id"));
        complexityScore.setComplexityScore(rs.getInt("complexity_score"));
        complexityScore.setLabelType(rs.getString("label_type"));
        complexityScore.setRateMultiplier(rs.getDouble("rate_multiplier"));
        return complexityScore;
    }
}
