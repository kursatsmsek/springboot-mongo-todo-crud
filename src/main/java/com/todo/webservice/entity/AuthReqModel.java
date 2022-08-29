package com.todo.webservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthReqModel {

    private String username;

    private String password;

}
