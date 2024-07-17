package com.management.users.infrastructure.controllers;

import com.management.users.domain.exceptions.UserAlreadyEnabledException;
import com.management.users.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private final UUID userId = UUID.randomUUID();

    private MockMvc mockMvc;

    @Mock
    private UserController userController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    public void testHandleNotFoundException() throws Exception {
        when(userController.getUserById(any(UUID.class))).thenThrow(new UserNotFoundException("User not found with id: " + userId));

        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("User not found with id: " + userId));
    }

    @Test
    public void testHandlePreconditionFailedException() throws Exception {
        when(userController.enableUser(any(UUID.class))).thenThrow(new UserAlreadyEnabledException("The user is already enabled."));

        mockMvc.perform(patch("/api/users/{userId}/enable", userId))
                .andExpect(status().isPreconditionFailed())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("The user is already enabled."));
    }

    @Test
    public void testHandleMethodArgumentNotValidException() throws Exception {
        mockMvc.perform(patch("/api/users/userId/enable"))
                .andExpect(status().isBadRequest());
    }

}