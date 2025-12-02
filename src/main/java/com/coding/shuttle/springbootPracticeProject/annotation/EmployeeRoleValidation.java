package com.coding.shuttle.springbootPracticeProject.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = EmployeeRoleValidator.class)
public @interface EmployeeRoleValidation {

    String message() default "Employee Role should be either ADMIN or USER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
