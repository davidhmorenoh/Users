package com.management.users.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

@Getter
@Setter
public class EmailFormatValidator implements ConstraintValidator<ValidEmailFormat, String> {

    @Value("${regex.email.regexp}")
    private String emailRegularExpression;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return Objects.isNull(email) || email.matches(getEmailRegularExpression());
    }

}