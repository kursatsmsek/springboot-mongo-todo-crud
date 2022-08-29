package com.todo.webservice.controller;

import com.todo.webservice.entity.ToDo;
import com.todo.webservice.repository.ToDoRepository;
import com.todo.webservice.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    ToDoService toDoService;

    @PostMapping("/createToDo")
    public ResponseEntity<String> createToDo(@RequestBody ToDo toDo) {
        try {
            toDoService.createToDoService(toDo);
            return ResponseEntity.ok("To-Do was added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @GetMapping("/getToDoList")
    public ResponseEntity<?> getToDoList() {
        try {
            return ResponseEntity.ok(toDoService.getToDoListService());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @DeleteMapping("/deleteToDo")
    public ResponseEntity<String> deleteToDo(@RequestParam String id) {
        try {
            toDoService.deleteToDoService(id);
            return ResponseEntity.ok("ToDo deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @PutMapping("/changeDoneStatus")
    public ResponseEntity<String> changeDoneStatus(@RequestParam String id, boolean val) {
        try {
            toDoService.changeDoneStatusService(id, val);
            return ResponseEntity.ok("ToDo done status changed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

}
