package com.universitymanagementserver.server.controllers.User;

import com.universitymanagementserver.server.models.UserModel;
import com.universitymanagementserver.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/protected/user")
public class UserProtectedController {

    @Autowired
    UserService userService;

    @PutMapping("/update")
    public ResponseEntity<UserModel> updateUserDetails(@RequestAttribute Integer userId, @RequestBody Map<String , Object> userMap)
    {
        UserModel user = userService.updateUserDetails(userId, userMap);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<UserModel> getUserDetails(@RequestAttribute Integer userId)
    {
        UserModel user = userService.getUserDetails(userId);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

}
