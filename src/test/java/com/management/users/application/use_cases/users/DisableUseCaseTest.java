package com.management.users.application.use_cases.users;

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
public class DisableUseCaseTest {

    @Mock
    private UserApplicationService userApplicationService;

    @InjectMocks
    private DisableUseCase disableUseCase;

    private UUID userId;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setActive(Boolean.FALSE);
    }

    @Test
    void testExecute() {
        when(userApplicationService.disableUser(userId)).thenReturn(userResponse);

        UserResponse disabledUser = disableUseCase.execute(userId);

        assertEquals(userId, disabledUser.getId());
        assertEquals(Boolean.FALSE, disabledUser.isActive());

        verify(userApplicationService, times(1)).disableUser(userId);
    }

}