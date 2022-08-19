package com.todo.webservice.customAnnotations.uniqueUsername;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(
        validatedBy = { UniqueUsernameValidator.class }
)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "Username must be unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
