package com.universitymanagementserver.server.services.user;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.UserModel;

public interface IUserService {

    UserModel registerUser(String name , String email , String password ) throws ServerAuthException;

    UserModel validatingUser(String email , String password) throws ServerAuthException;
}
