package com.universitymanagementserver.server.services;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.StudentModel;
import com.universitymanagementserver.server.repositories.StudentRepository;
import com.universitymanagementserver.server.repositories.StudentRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentModel ValidatingStudent(String email, String password) throws ServerAuthException {
        return null;
    }

    @Override
    public StudentModel RegisterStudent(String name, String email, String password) throws ServerAuthException {
        Pattern pattern =  Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new ServerAuthException("Invalid email");
        Integer count = studentRepository.GetCountByEmail(email);
        if(count > 0)
            throw new ServerAuthException("Email already in user");
    Integer studentId = studentRepository.CreateStudent(name , email , password);
    return studentRepository.FindById(studentId);
    }
}
