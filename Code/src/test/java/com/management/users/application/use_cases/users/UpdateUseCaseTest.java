package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.services.UserApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateUseCaseTest {

    @Mock
    private UserApplicationService userApplicationService;

    @InjectMocks
    private UpdateUseCase updateUseCase;

    private UUID userId;
    private UserRequest userRequest;
    private UserResponse updatedUserResponse;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        userRequest = new UserRequest();
        userRequest.setName("Updated User");
        userRequest.setEmail("updated@user.com");

        updatedUserResponse = new UserResponse();
        updatedUserResponse.setId(userId);
    }

    @Test
    void testExecute() {
        when(userApplicationService.updateUser(userId, userRequest)).thenReturn(updatedUserResponse);

        UserResponse returnedUser = updateUseCase.execute(userId, userRequest);

        assertEquals(userId, returnedUser.getId());

        verify(userApplicationService, times(1)).updateUser(userId, userRequest);
    }

}