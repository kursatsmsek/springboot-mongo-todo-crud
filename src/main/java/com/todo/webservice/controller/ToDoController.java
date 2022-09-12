package com.todo.webservice.controller;

import com.todo.webservice.entity.MyUser;
import com.todo.webservice.entity.ToDo;
import com.todo.webservice.repository.ToDoRepository;
import com.todo.webservice.service.ToDoService;
import com.todo.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    ToDoService toDoService;

    @Autowired
    UserService userService;

    @PostMapping("/createToDo")
    public ResponseEntity<String> createToDo(@RequestBody ToDo toDo, Principal principal) {
        try {
            String username = principal.getName();
            MyUser toDoOwner = userService.getMyUserByUsername(username);
            toDo.setUser(toDoOwner);
            toDoService.createToDoService(toDo);
            return ResponseEntity.ok("To-Do was added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @GetMapping("/getToDoList")
    public ResponseEntity<?> getToDoList(Principal principal) {
        try {
            return ResponseEntity.ok(toDoService.getUserToDoListService(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @DeleteMapping("/deleteToDo")
    public ResponseEntity<String> deleteToDo(@RequestParam String id, Principal principal) {
        try {
            toDoService.deleteToDoService(id, principal.getName());
            return ResponseEntity.ok("ToDo deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

    @PutMapping("/changeDoneStatus")
    public ResponseEntity<String> changeDoneStatus(@RequestParam String id, boolean val, Principal principal) {
        try {
            toDoService.changeDoneStatusService(id, val, principal.getName());
            return ResponseEntity.ok("ToDo done status changed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }

}
