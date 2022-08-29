package com.todo.webservice.service;

import com.todo.webservice.entity.MyUser;
import com.todo.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public void createUser(MyUser user) {
        userRepository.save(user);
    }

    public List<MyUser> getAllUsers() { return userRepository.findAll(); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository.findByUsername(username);

        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

        return org.springframework.security.core.userdetails.User
                .withUserDetails(securityUser)
                .build();
    }
}
