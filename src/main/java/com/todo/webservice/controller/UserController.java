package com.todo.webservice.controller;

import com.todo.webservice.entity.AuthReqModel;
import com.todo.webservice.entity.MyUser;
import com.todo.webservice.security.JwtUtil;
import com.todo.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String getWelcome() {
        return "Welcome";
    }

    @GetMapping("/getByUsername")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        try {
            return ResponseEntity.ok(userService.loadUserByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> add(@Valid @RequestBody MyUser user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @PostMapping("/login")
    public Map<String, String> createToken(@RequestBody AuthReqModel authReqModel) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReqModel.getUsername(), authReqModel.getPassword()));
        } catch (Exception ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authReqModel.getUsername());
        MyUser myUser = userService.getMyUserByUsername(authReqModel.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> result = new HashMap<>();
        result.put("userId", myUser.getId());
        result.put("userName", myUser.getName());
        result.put("token", jwt);

        return result;
    }

}
