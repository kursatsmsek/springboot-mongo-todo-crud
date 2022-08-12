package com.todo.webservice.controller;

import com.todo.webservice.entity.ToDo;
import com.todo.webservice.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    ToDoRepository toDoRepository;

    @PostMapping("/createToDo")
    public ResponseEntity<String> createToDo(@RequestBody ToDo toDo) {
        toDoRepository.save(toDo);
        return ResponseEntity.ok("To-Do was added successfully.");
    }

    @GetMapping("/getToDoList")
    public ResponseEntity<List<ToDo>> getToDoList() {
        return ResponseEntity.ok(toDoRepository.findAll());
    }

    @DeleteMapping("/deleteToDo")
    public ResponseEntity<String> deleteToDo(@RequestParam String id) {
        toDoRepository.deleteById(id);
        return ResponseEntity.ok("ToDo deleted.");
    }

    @PutMapping("/changeDoneStatus")
    public ResponseEntity<String> changeDoneStatus(@RequestParam String id, boolean val) {
        List<ToDo> toDoList = toDoRepository.findAll();
        toDoList.forEach(item -> {
            if (item.getId().equals(id)) {
                item.setDone(val);
            }
        });

        toDoRepository.saveAll(toDoList);
        return ResponseEntity.ok("ToDo Changed.");
    }

    @PutMapping("/updateToDo")
    public ResponseEntity<String> updateToDo(@RequestParam String id) {
        List<ToDo> toDoList = toDoRepository.findAll();
        toDoList.forEach(item -> {
            if (item.getId().equals(id)) {
                item.setContent("Changed ToDo.");
            }
        });

        toDoRepository.saveAll(toDoList);
        return ResponseEntity.ok("ToDo Changed.");
    }

}
