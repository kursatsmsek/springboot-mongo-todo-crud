package com.todo.webservice.controller;

import com.todo.webservice.entity.User;
import com.todo.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User initialUser = new User();
        initialUser.setName("Adem");
        userRepository.save(initialUser);
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
