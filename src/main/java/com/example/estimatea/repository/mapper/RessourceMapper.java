package com.example.estimatea.repository.mapper;

import com.example.estimatea.model.Ressource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RessourceMapper implements RowMapper<Ressource> {

    @Override
    public Ressource mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ressource ressource = new Ressource();
        ressource.setRessourceId(rs.getInt("res_id"));
        ressource.setRessourceName(rs.getString("res_name"));
        ressource.setRessourceRate(rs.getInt("res_rate"));
        return ressource;
    }


}
