package com.management.users.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class EmailFormatValidator implements ConstraintValidator<ValidEmailFormat, String> {

    @Value("${regex.email.regexp}")
    private String emailRegularExpression;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (Objects.isNull(email)) {
            return true;
        }
        return email.matches(emailRegularExpression);
    }

}