package com.universitymanagementserver.server.repositories.User;

import com.universitymanagementserver.server.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface IUserRepository {

    UserModel findUserById(int userId);
    UserModel findUserByEmailPassword(String email , String password);
    Integer findUserCountByEmail(String email);
    Integer createUser(String name , String email , String password);
    Integer updateUSerDetails(int userId , Map<String , Object> attributes);
}
