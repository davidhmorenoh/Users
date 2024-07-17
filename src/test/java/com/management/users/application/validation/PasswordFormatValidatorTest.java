package com.management.users.application.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PasswordFormatValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private PasswordFormatValidator validator;

    @BeforeEach
    void setUp() {
        validator.setPasswordRegularExpression("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$");
    }

    @Test
    void testValidPassword() {
        String validPassword = "Password1@";
        assertTrue(validator.isValid(validPassword, context));
    }

    @Test
    void testNullPassword() {
        assertTrue(validator.isValid(null, context));
    }

    @Test
    void testInvalidPassword() {
        String invalidPassword = "invalid-password";
        assertFalse(validator.isValid(invalidPassword, context));
    }

}