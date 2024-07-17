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
public class EmailFormatValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private EmailFormatValidator validator;

    @BeforeEach
    void setUp() {
        validator.setEmailRegularExpression("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    @Test
    void testValidEmail() {
        String validEmail = "test@example.com";
        assertTrue(validator.isValid(validEmail, context));
    }

    @Test
    void testNullEmail() {
        assertTrue(validator.isValid(null, context));
    }

    @Test
    void testInvalidEmail() {
        String invalidEmail = "invalid-email";
        assertFalse(validator.isValid(invalidEmail, context));
    }

}