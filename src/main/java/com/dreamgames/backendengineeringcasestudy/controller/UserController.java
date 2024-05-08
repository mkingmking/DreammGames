package com.dreamgames.backendengineeringcasestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamgames.backendengineeringcasestudy.User;
import com.dreamgames.backendengineeringcasestudy.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser() {
        User user = userService.createUser();
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/level")
    public ResponseEntity<User> updateLevel(@PathVariable Long id) {
        User updatedUser = userService.updateLevel(id);
        return ResponseEntity.ok(updatedUser);
    }
}
