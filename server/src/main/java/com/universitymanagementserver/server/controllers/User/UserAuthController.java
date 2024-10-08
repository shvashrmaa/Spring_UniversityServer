package com.universitymanagementserver.server.controllers.User;

import com.universitymanagementserver.server.models.UserModel;
import com.universitymanagementserver.server.services.user.UserService;
import com.universitymanagementserver.server.utils.GenerateTokenKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserAuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String name = userMap.get("name").toString();
        String password = userMap.get("password").toString();
        String email = userMap.get("email").toString();
        UserModel user = userService.registerUser(name, email, password);
        return new ResponseEntity<>(GenerateTokenKey.GenrateJWTToken(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = userMap.get("email").toString();
        String password = userMap.get("password").toString();
        UserModel user = userService.validatingUser(email , password);
        return new ResponseEntity<>(GenerateTokenKey.GenrateJWTToken(user),HttpStatus.OK);
    }
}
