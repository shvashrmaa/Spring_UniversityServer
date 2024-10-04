package com.universitymanagementserver.server.repositories;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.FacultyModel;

public interface FacultyRepository {

    Integer CreateFaculty(String name , String email , String password) throws ServerAuthException;

    FacultyModel FindByEmailAndPassword(String email , String  password) throws ServerAuthException;

    Integer FindByEmail(String email) throws ServerAuthException;

    FacultyModel FindById(Integer facultyId) throws ServerAuthException;
}
