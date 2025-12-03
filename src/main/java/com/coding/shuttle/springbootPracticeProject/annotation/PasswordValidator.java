package com.coding.shuttle.springbootPracticeProject.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        if(password == null) return false;

        if(password.length() < 10) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;

        String specialChars = "@#$%^&+=!(){}[]-_?<>.*";

        for(char c : password.toCharArray()){

            if(Character.isUpperCase(c)) hasUpper = true;
            else if(Character.isLowerCase(c)) hasLower = true;
            else if(specialChars.contains(String.valueOf(c))) hasSpecial = true;

        }
        return hasSpecial && hasLower && hasUpper;
    }
}
