package com.todo.webservice.repository;

import com.todo.webservice.entity.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<MyUser, String> {
   MyUser findByUsername(String username);

   MyUser findByEmail(String email);

}
