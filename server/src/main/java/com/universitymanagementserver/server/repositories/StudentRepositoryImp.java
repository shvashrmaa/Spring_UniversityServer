package com.universitymanagementserver.server.repositories;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class StudentRepositoryImp implements StudentRepository {

    private static final String SQL_CREATE = "insert into student(userId , name , email , password) values(NEXTVAL('student_seq'),?,?,?)";
    private static final String SQL_COUNT_BY_EMAIL = "select COUNT(*) from student where email=?";
    private static final String SQL_FIND_BY_ID = "select userId , name , email , password from student where id=?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer CreateStudent(String name, String email, String password) throws ServerAuthException {
        try
        {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE , Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                return ps;
            } , keyHolder);
            return (Integer) keyHolder.getKeys().get("userId");
        }catch (Exception e)
        {
            throw new ServerAuthException("Invalid Details. Failed to create student");
        }
    }


    @Override
    public StudentModel FindByEmailAndPassword(String email, String password) throws ServerAuthException {
        return null;
    }

    @Override
    public Integer GetCountByEmail(String email) throws ServerAuthException {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL,new Object[]{email}, Integer.class);
    }

    @Override
    public StudentModel FindById(Integer studentId) throws ServerAuthException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID , new Object[]{studentId}, StudentRowMapper);
    }

    private final RowMapper<StudentModel> StudentRowMapper = ((res , rowNum) ->
    {
        return new StudentModel(res.getInt("userId"),
                res.getString("name"),
                res.getString("email"),
                res.getString("password"));
    });

}
