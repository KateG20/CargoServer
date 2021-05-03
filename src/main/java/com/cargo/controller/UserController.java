package com.cargo.controller;

import com.cargo.entity.Credentials;
import com.cargo.entity.Key;
import com.cargo.entity.Request;
import com.cargo.entity.User;
import com.cargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            if (createdUser == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/check/credentials")
    public ResponseEntity<User> checkCredentials(@RequestBody Credentials cred) {
        User user = userService.checkCredentials(cred);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/user/check/key")
    public ResponseEntity<Key> checkKey(@RequestBody Key key) {
        Key foundKey = userService.checkKey(key);
        if (foundKey != null) {
            return new ResponseEntity<>(foundKey, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
