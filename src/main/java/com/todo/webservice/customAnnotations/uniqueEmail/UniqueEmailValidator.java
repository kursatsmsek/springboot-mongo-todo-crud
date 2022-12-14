package com.todo.webservice.customAnnotations.uniqueEmail;

import com.todo.webservice.entity.MyUser;
import com.todo.webservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        MyUser myUser = userRepository.findByEmail(s);
        return myUser == null;
    }
}
