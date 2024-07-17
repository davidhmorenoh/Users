package com.management.users.infrastructure.controllers;

import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.use_cases.users.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private GetAllUseCase getAllUseCase;

    @Mock
    private GetByIdUseCase getByIdUseCase;

    @Mock
    private CreateUseCase createUseCase;

    @Mock
    private UpdateUseCase updateUseCase;

    @Mock
    private EnableUseCase enableUseCase;

    @Mock
    private DisableUseCase disableUseCase;

    @Mock
    private DeleteUseCase deleteUseCase;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        // Mock data
        UserResponse userResponse = new UserResponse();
        userResponse.setId(UUID.randomUUID());
        List<UserResponse> userResponses = Collections.singletonList(userResponse);
        when(getAllUseCase.execute()).thenReturn(userResponses);

        // Call controller method
        ResponseEntity<List<UserResponse>> responseEntity = userController.getAllUsers();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponses, responseEntity.getBody());
    }

    @Test
    public void testGetUserById() {
        // Mock data
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(UUID.randomUUID());
        when(getByIdUseCase.execute(userId)).thenReturn(userResponse);

        // Call controller method
        ResponseEntity<UserResponse> responseEntity = userController.getUserById(userId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    public void testCreateUser() {
        // Mock data
        UUID userId = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword("password2@S");
        userRequest.setEmail("test@example.com");
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        when(createUseCase.execute(any(UserRequest.class))).thenReturn(userResponse);

        // Call controller method
        ResponseEntity<UserResponse> responseEntity = userController.createUser(userRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() {
        // Mock data
        UUID userId = UUID.randomUUID();
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("newemail@example.com");
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        when(updateUseCase.execute(eq(userId), any(UserRequest.class))).thenReturn(userResponse);

        // Call controller method
        ResponseEntity<UserResponse> responseEntity = userController.updateUser(userId, userRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    public void testEnableUser() {
        // Mock data
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(UUID.randomUUID());
        when(enableUseCase.execute(userId)).thenReturn(userResponse);

        // Call controller method
        ResponseEntity<UserResponse> responseEntity = userController.enableUser(userId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    public void testDisableUser() {
        // Mock data
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(UUID.randomUUID());
        when(disableUseCase.execute(userId)).thenReturn(userResponse);

        // Call controller method
        ResponseEntity<UserResponse> responseEntity = userController.disableUser(userId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        // Call controller method
        ResponseEntity<Void> responseEntity = userController.deleteUser(UUID.randomUUID());

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(deleteUseCase, times(1)).execute(any(UUID.class));
    }

}