package com.coding.shuttle.springbootPracticeProject.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");

        return roles.contains(inputRole);
    }
}
