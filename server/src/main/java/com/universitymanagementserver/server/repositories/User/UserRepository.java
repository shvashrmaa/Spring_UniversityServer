package com.universitymanagementserver.server.repositories.User;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.exceptions.ServerBadRequestException;
import com.universitymanagementserver.server.models.UserModel;
import com.universitymanagementserver.server.utils.SQLQueries;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserModel findUserById(int userId) {
        return jdbcTemplate.queryForObject(SQLQueries.SQL_FIND_USER_BY_ID , userRowMapper , userId);
    }

    @Override
    public UserModel findUserByEmailPassword(String email, String password) {
        try{

            UserModel user = jdbcTemplate.queryForObject(SQLQueries.SQL_FIND_USER_BY_EMAIL , userRowMapper , email );
            assert user != null;
            if(!BCrypt.checkpw(password , user.getPassword())) {
                throw new ServerBadRequestException("Invalid password or email");
            }
            return user;
        }catch (Exception e){
            System.out.println(e);
            throw new ServerBadRequestException("Invalid email or password");
        }
    }

    @Override
    public Integer findUserCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQLQueries.SQL_FIND_USER_COUNT_BY_EMAIL , Integer.class , email);
    }

    @Override
    public Integer createUser(String name, String email, String password) {
        String hashedPassword = BCrypt.hashpw(password , BCrypt.gensalt());
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection ->
            {
                PreparedStatement ps = connection.prepareStatement(SQLQueries.SQL_CREATE_USER , Statement.RETURN_GENERATED_KEYS);
                ps.setString(1 , name);
                ps.setString(2 , email);
                ps.setString(3 , hashedPassword);
                return ps;
            } , keyHolder);
            return (Integer) keyHolder.getKeys().get("userId");
        }catch (Exception e){
            System.out.println(e);
            throw new ServerBadRequestException("Invalid Details, Create Faculty failed");
        }
    }

    private RowMapper<UserModel> userRowMapper = ((rs, rowNum) ->
    {
        return new UserModel(rs.getInt("userId"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("profilePicUrl")
                );
    });
}
