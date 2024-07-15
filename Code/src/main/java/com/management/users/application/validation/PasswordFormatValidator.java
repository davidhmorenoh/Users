package com.management.users.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class PasswordFormatValidator implements ConstraintValidator<ValidPasswordFormat, String> {

    @Value("${regex.password.regexp}")
    private String passwordRegularExpression;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (Objects.isNull(password)) {
            return true;
        }
        return password.matches(passwordRegularExpression);
    }

}