package com.example.demo.Custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidater.class)
@Target({ FIELD,TYPE,ANNOTATION_TYPE })
public @interface mobileValidate {
public String message() default "Invalid Mobile Number";
Class<?>[] groups() default{};
Class<?extends Payload>[] payload() default {};
}
