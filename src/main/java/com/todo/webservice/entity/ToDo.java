package com.todo.webservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ToDo {

    @Id
    private String id;

    private String content;

    private String statusColor;

    private String title;

    private User user;

    private boolean done;

}
