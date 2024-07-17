package com.management.users.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

@Getter
@Setter
public class PasswordFormatValidator implements ConstraintValidator<ValidPasswordFormat, String> {

    @Value("${regex.password.regexp}")
    private String passwordRegularExpression;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return Objects.isNull(password) || password.matches(getPasswordRegularExpression());
    }

}