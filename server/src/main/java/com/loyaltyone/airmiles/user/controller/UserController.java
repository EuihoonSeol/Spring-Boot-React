package com.loyaltyone.airmiles.user.controller;

import com.loyaltyone.airmiles.user.model.User;
import com.loyaltyone.airmiles.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> param) {
        return userService.createUser(param);
    }

}
