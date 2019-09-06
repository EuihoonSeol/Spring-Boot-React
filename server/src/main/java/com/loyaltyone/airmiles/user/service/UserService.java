package com.loyaltyone.airmiles.user.service;

import com.loyaltyone.airmiles.user.model.User;
import com.loyaltyone.airmiles.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createUser(Map<String, String> param) {
        User user = new User();
        user.setName(param.get("Name"));

        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}