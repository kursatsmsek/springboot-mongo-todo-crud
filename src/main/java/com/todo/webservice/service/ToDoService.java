package com.todo.webservice.service;

import com.todo.webservice.entity.MyUser;
import com.todo.webservice.entity.ToDo;
import com.todo.webservice.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    @Autowired
    UserService userService;

    public void createToDoService(ToDo toDo) throws Exception {
        // The content area can be blank
        if (toDo.getTitle() == null || toDo.getTitle().isBlank()) throw new Exception();
        if (toDo.getStatusColor() == null || toDo.getStatusColor().isBlank()) throw new Exception();
        //if (toDo.getUser() == null || toDo.getUser().getName().isBlank()) throw new Exception();
        // Check user presence
        toDoRepository.save(toDo);
    }

    public List<ToDo> getUserToDoListService(String username) throws Exception {
        MyUser toDoOwner = userService.getMyUserByUsername(username);
        return toDoRepository.findByUser(toDoOwner);
    }

    public List<ToDo> getToDoListService() {
        return toDoRepository.findAll();
    }

    public void deleteToDoService(String id, String username) throws Exception {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) throw new Exception();
        ToDo todo = optionalToDo.get();
        MyUser toDoOwner = todo.getUser();
        if (toDoOwner.getUsername().equals(username)) {
            toDoRepository.deleteById(id);
        } else {
            throw new Exception("Permission Denied");
        }
    }

    public void changeDoneStatusService(String id, boolean val, String username) throws Exception {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) throw new Exception();
        ToDo todo = optionalToDo.get();
        MyUser toDoOwner = todo.getUser();
        if (toDoOwner.getUsername().equals(username)) {
            List<ToDo> toDoList = toDoRepository.findAll();
            toDoList.forEach(item -> {
                if (item.getId().equals(id)) {
                    item.setDone(val);
                }
            });
            toDoRepository.saveAll(toDoList);
        } else {
            throw new Exception("Permission Denied");
        }
    }

}
