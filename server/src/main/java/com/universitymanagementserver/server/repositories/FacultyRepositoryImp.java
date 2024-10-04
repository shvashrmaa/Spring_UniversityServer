package com.universitymanagementserver.server.repositories;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.FacultyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

@Repository
public class FacultyRepositoryImp implements FacultyRepository {
    private static final String SQL_CREATE_FACULTY = "insert into faculty(userId , name , email , password) values(NEXTVAL('faculty_seq') , ? , ? , ?)";
    private static final String SQL_FIND_BY_EMAIL = "select count(*) from faculty where email = ?";
    private static final String SQL_FIND_BY_ID = "select userId , name , email , password from faculty where userId = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer CreateFaculty(String name , String email , String password){
        try{
            KeyHolder keyholder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->
            {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_FACULTY , Statement.RETURN_GENERATED_KEYS);
                ps.setString(1  ,name);
                ps.setString(2, email);
                ps.setString(3, password);
                return ps;
            },keyholder);
            return (Integer) keyholder.getKeys().get("userId");
        }catch (Exception e){
            System.out.println(e);
            throw new ServerAuthException("Invalid Details, Create Faculty failed");
        }
    }

    @Override
    public FacultyModel FindByEmailAndPassword(String email, String password) throws ServerAuthException {
        return null;
    }

    @Override
    public Integer FindByEmail(String email) throws ServerAuthException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public FacultyModel FindById(Integer facultyId) throws ServerAuthException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID,new Object[]{facultyId} , FacultyRowMapper);
    }

    private final RowMapper<FacultyModel> FacultyRowMapper = ((res , rowNum) ->
    {
        return new FacultyModel(res.getInt("userId"),
                res.getString("name"),
                res.getString("email"),
                res.getString("password"));
    });
}
