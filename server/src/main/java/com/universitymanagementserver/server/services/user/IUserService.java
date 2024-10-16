package com.universitymanagementserver.server.services.user;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.exceptions.ServerBadRequestException;
import com.universitymanagementserver.server.exceptions.ServerConflictException;
import com.universitymanagementserver.server.exceptions.ServerNotFoundException;
import com.universitymanagementserver.server.models.UserModel;

import java.util.Map;

public interface IUserService {

    UserModel registerUser(String name , String email , String password ) throws ServerAuthException;

    UserModel validatingUser(String email , String password) throws ServerAuthException;

    UserModel updateUserDetails(int userId , Map<String , Object> attributes) throws ServerAuthException , ServerBadRequestException , ServerNotFoundException , ServerConflictException;

    UserModel getUserDetails(int userId) throws ServerNotFoundException , ServerBadRequestException , ServerAuthException;
}
