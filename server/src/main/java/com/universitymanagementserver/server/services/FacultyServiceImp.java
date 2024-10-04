package com.universitymanagementserver.server.services;

import com.universitymanagementserver.server.exceptions.ServerAuthException;
import com.universitymanagementserver.server.models.FacultyModel;
import com.universitymanagementserver.server.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class FacultyServiceImp implements FacultyService {
    @Autowired
    FacultyRepository facultyRepository;


    @Override
    public FacultyModel ValidatingFaculty(String email, String password) throws ServerAuthException {
        return null;
    }

    @Override
    public FacultyModel RegisterFaculty(String name, String email, String password) throws ServerAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null)
            email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new ServerAuthException("Invalid email");
        Integer count = facultyRepository.FindByEmail(email);
        if(count > 0)
            throw new ServerAuthException("Email already exists");
        Integer faculty = facultyRepository.CreateFaculty(name , email , password);
        return facultyRepository.FindById(faculty);
    }
}
