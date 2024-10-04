package com.universitymanagementserver.server.services;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.FacultyModel;

public interface FacultyService {
    FacultyModel ValidatingFaculty(String email , String password) throws ServerAuthException;

    FacultyModel RegisterFaculty(String name , String email , String password ) throws ServerAuthException;
}
