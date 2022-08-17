package com.todo.webservice.repository;

import com.todo.webservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
   User findByUsername(String username);
}
