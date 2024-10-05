package com.universitymanagementserver.server.services;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.StudentModel;
import org.apache.catalina.Server;

public interface StudentService {

    StudentModel ValidatingStudent(String email , String password) throws ServerAuthException;

    StudentModel RegisterStudent(String name , String email , String password) throws ServerAuthException;

    StudentModel GetStudentDetailsById(Integer userId) throws ServerAuthException;
}
