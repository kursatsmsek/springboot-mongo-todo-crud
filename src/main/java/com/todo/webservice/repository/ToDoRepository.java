package com.todo.webservice.repository;

import com.todo.webservice.entity.MyUser;
import com.todo.webservice.entity.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
    List<ToDo> findByUser(MyUser user);

    Optional<ToDo> findById(String id);
}
