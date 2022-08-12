package com.todo.webservice.controller;

import com.todo.webservice.entity.User;
import com.todo.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User initialUser = new User();
        initialUser.setName("Adem");
        userRepository.save(initialUser);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user.toString());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
