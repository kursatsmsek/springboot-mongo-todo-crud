package com.todo.webservice.service;

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

    public void createToDoService(ToDo toDo) throws Exception {
        // The content area can be blank
        if (toDo.getTitle() == null || toDo.getTitle().isBlank()) throw new Exception();
        if (toDo.getStatusColor() == null || toDo.getStatusColor().isBlank()) throw new Exception();
        if (toDo.getUser() == null || toDo.getUser().getName().isBlank()) throw new Exception();
        // Check user presence
        toDoRepository.save(toDo);
    }

    public List<ToDo> getToDoListService() {
        return toDoRepository.findAll();
    }

    public void deleteToDoService(String id) throws Exception {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (!toDo.isPresent()) throw new Exception();
        toDoRepository.deleteById(id);
    }

    public void changeDoneStatusService(String id, boolean val) throws Exception {
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if (!toDo.isPresent()) throw new Exception();

        List<ToDo> toDoList = toDoRepository.findAll();
        toDoList.forEach(item -> {
            if (item.getId().equals(id)) {
                item.setDone(val);
            }
        });
        toDoRepository.saveAll(toDoList);
    }

}
