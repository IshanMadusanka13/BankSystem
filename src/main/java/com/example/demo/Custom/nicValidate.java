package com.example.demo.Custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NicValidater.class)
@Target({ FIELD,TYPE,ANNOTATION_TYPE })
public @interface nicValidate {
    public String message() default "Invalid NIC Number";
    Class<?>[] groups() default{};
    Class<?extends Payload>[] payload() default {};
}
