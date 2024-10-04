package com.universitymanagementserver.server.controllers;

import com.universitymanagementserver.server.Constant;
import com.universitymanagementserver.server.models.FacultyModel;
import com.universitymanagementserver.server.services.FacultyService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/register")
    public ResponseEntity<Map<String , String>> registerFaculty(@RequestBody Map<String , Object> facultyMap)
    {
        String name = (String) facultyMap.get("name");
        String email = (String) facultyMap.get("email");
        String password = (String) facultyMap.get("password");
        FacultyModel faculty = facultyService.RegisterFaculty(name , email , password);
        return new ResponseEntity<>(generateToken(faculty) , HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String , String>> loginfaculty(@RequestBody Map<String , Object> facultyMap)
    {
        String email = (String) facultyMap.get("email");
        String password = (String) facultyMap.get("password");
        FacultyModel faculty = facultyService.ValidatingFaculty(email , password);
        return new ResponseEntity<>(generateToken(faculty) , HttpStatus.OK);
    }

    private Map<String , String> generateToken(FacultyModel faculty)
    {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256 , Constant.SECRET_API_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constant.EXPIRATION_TIME))
                .claim("userId" , faculty.getUserId())
                .claim("name" , faculty.getName())
                .claim("email" , faculty.getPassword())
                .compact();

        Map<String , String> map = new HashMap<>();
        map.put("token" , token);
        return map;

    }
}
