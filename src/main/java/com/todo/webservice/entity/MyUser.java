package com.todo.webservice.entity;

import com.todo.webservice.customAnnotations.uniqueEmail.UniqueEmail;
import com.todo.webservice.customAnnotations.uniqueUsername.UniqueUsername;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Document
public class MyUser {

    @Id
    private String id;

    @NotBlank(message = "Please provide a valid name.")
    @Size(min = 3, max = 27, message = "Name size must be between 3 and 27.")
    private String name;

    @NotBlank(message = "Please provide a valid username.")
    @Size(min = 3, max = 27, message = "Username size must be between 3 and 27.")
    @UniqueUsername(message = "Username already exists.")
    private String username;

    @Email(message = "Please provide a valid e-mail.")
    @UniqueEmail(message = "Email already exists.")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}] :;',?/*~$^+=<>]).{8,27}$",
            message = "Password must contain at least one uppercase character, one lowercase character, " +
                    "and one special character and one number.")
    private String password;
}
