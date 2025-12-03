package com.coding.shuttle.springbootPracticeProject.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PrimeNumberValidator.class)
public @interface PrimeNumber {
    String message() default "Department Code should be a prime number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

