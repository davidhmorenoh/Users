package com.management.users.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDSizeValidator implements ConstraintValidator<ValidUUIDSize, UUID> {

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext context) {
        if (uuid == null) {
            return true;
        }
        return uuid.toString().length() == 36;
    }

}