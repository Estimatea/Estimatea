package com.example.estimatea.repository.jdbc;


import com.example.estimatea.repository.mapper.SubProjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubProjectRepository {

    private final SubProjectMapper subMapper;
    private final JdbcTemplate jdbc;

    //SQL statements for subproject table
    private final String CREATE_SUPROJECT = "INSERT INTO sub_project(start_date, ,deadline, completed, sub_name, project_id) VALUES (?,?,?,?,?,?)";
    private final String DELETE_SUBPROJECT = "DELETE FROM sub_project WHERE sub_id = ?";
    private final String EDIT_DEADLINE = "UPDATE sub_project SET deadline = ? WHERE sub_id = ?";
    private final String EDIT_COMPLETED = "UPDATE sub_project SET completed = ? WHERE sub_id = ?";

    //SQL statements for subproject employees



    public SubProjectRepository(SubProjectMapper subMapper, JdbcTemplate jdbc) {
        this.subMapper = subMapper;
        this.jdbc = jdbc;
    }


}
