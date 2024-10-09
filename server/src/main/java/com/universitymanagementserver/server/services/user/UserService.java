package com.universitymanagementserver.server.services.user;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.exceptions.ServerConflictException;
import com.universitymanagementserver.server.exceptions.ServerNotModifyException;
import com.universitymanagementserver.server.models.UserModel;
import com.universitymanagementserver.server.repositories.User.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService implements  IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserModel registerUser(String name, String email, String password) throws ServerAuthException, ServerConflictException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email == null) throw new ServerAuthException("Email Required!!");
        if(!pattern.matcher(email).matches()) throw new ServerAuthException("Email Format Error!!");
        email = email.toLowerCase();
        Integer count = userRepository.findUserCountByEmail(email);
        if(count > 0) throw new ServerConflictException("Email Already Exist!!");
        Integer userId = userRepository.createUser(name , email , password);
        return userRepository.findUserById(userId);
    }

    @Override
    public UserModel validatingUser(String email, String password) throws ServerAuthException {
        if(email != null)
            email = email.toLowerCase();
        return userRepository.findUserByEmailPassword(email , password);
    }

    @Override
    public UserModel updateUserDetails(int userId , Map<String , Object> attributes) {
        int count = userRepository.updateUSerDetails(userId , attributes);
        if(count > 0) return userRepository.findUserById(userId);
        throw new ServerNotModifyException("Updates not successful");
    }
}
