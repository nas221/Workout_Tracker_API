package com.workouttracker.auth.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "displayName.empty", "Display name is required");

        RegisterRequest registerRequest = (RegisterRequest) target;
        if (registerRequest.email() != null && !registerRequest.email().contains("@")) {
            errors.rejectValue("email", "email.invalid", "Email is invalid");
        }
    }
}
