package com.universitymanagementserver.server.repositories;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.StudentModel;

public interface StudentRepository {

    Integer CreateStudent(String name , String email , String password) throws ServerAuthException;

    StudentModel FindByEmailAndPassword(String email , String password) throws ServerAuthException;

    Integer GetCountByEmail(String email) throws ServerAuthException;

    StudentModel FindById(Integer studentId) throws ServerAuthException;
}
