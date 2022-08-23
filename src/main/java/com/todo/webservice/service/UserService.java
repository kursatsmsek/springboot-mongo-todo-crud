package com.todo.webservice.service;

import com.todo.webservice.entity.User;
import com.todo.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserByUsername(String username) { return userRepository.findByUsername(username); }

    public List<User> getAllUsers() { return userRepository.findAll(); }

}