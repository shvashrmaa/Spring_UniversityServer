package com.universitymanagementserver.server.controllers;


import com.universitymanagementserver.server.models.StudentModel;
import com.universitymanagementserver.server.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Map<String , String>> registerStudent(@RequestBody Map<String , Object> StudentMap)
    {
        String name = (String) StudentMap.get("name");
        String email = (String) StudentMap.get("email");
        String password = (String) StudentMap.get("password");

        StudentModel student = studentService.RegisterStudent(name , email , password);
        Map<String , String> map = new HashMap<>();
        map.put("message","registered successfully");
        return new ResponseEntity<>(map , HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String , String>> loginStudent(@RequestBody Map<String , Object> StudentMap)
    {
        String email = (String) StudentMap.get("email");
        String password = (String) StudentMap.get("password");

        StudentModel student = studentService.ValidatingStudent(email , password);
        Map<String , String> map = new HashMap<>();
        map.put("message","login successfully");
        return new ResponseEntity<>(map , HttpStatus.OK);
    }

}
