package com.todo.webservice.customAnnotations.uniqueUsername;

import com.todo.webservice.entity.User;
import com.todo.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByUsername(s);
        return user == null;
    }
}
