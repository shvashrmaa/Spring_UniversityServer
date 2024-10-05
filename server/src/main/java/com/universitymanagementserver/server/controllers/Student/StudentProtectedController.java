package com.universitymanagementserver.server.controllers.Student;

import com.universitymanagementserver.server.models.StudentModel;
import com.universitymanagementserver.server.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/protected/student")
public class StudentProtectedController {

    @Autowired
    StudentService studentService;

    @GetMapping("")
    public ResponseEntity<StudentModel> GetStudentDetails(@RequestAttribute Integer userId)
    {
        StudentModel studentModel = studentService.GetStudentDetailsById(userId);
        return new ResponseEntity<>(studentModel , HttpStatus.OK);
    }
}
