package com.universitymanagementserver.server.controllers.Student;

import com.universitymanagementserver.server.Constant;
import com.universitymanagementserver.server.models.StudentModel;
import com.universitymanagementserver.server.services.StudentService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentAuthController {

    @Autowired
    StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<Map<String , String>> registerStudent(@RequestBody Map<String , Object> StudentMap)
    {
        String name = (String) StudentMap.get("name");
        String email = (String) StudentMap.get("email");
        String password = (String) StudentMap.get("password");

        StudentModel student = studentService.RegisterStudent(name , email , password);
        return new ResponseEntity<>(generateJWTToken(student) , HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String , String>> loginStudent(@RequestBody Map<String , Object> StudentMap)
    {
        String email = (String) StudentMap.get("email");
        String password = (String) StudentMap.get("password");

        StudentModel student = studentService.ValidatingStudent(email , password);
        return new ResponseEntity<>(generateJWTToken(student) , HttpStatus.OK);
    }

    private Map<String , String> generateJWTToken(StudentModel student)
    {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(Constant.SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constant.EXPIRATION_TIME))
                .claim("userId" , student.getUserId())
                .claim("name" , student.getName())
                .claim("email" , student.getEmail())
                .compact();

        Map<String , String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}