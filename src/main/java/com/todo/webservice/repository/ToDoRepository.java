package com.todo.webservice.repository;

import com.todo.webservice.entity.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
