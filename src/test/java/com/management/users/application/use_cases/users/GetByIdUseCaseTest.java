package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.services.UserApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetByIdUseCaseTest {

    @Mock
    private UserApplicationService userApplicationService;

    @InjectMocks
    private GetByIdUseCase getByIdUseCase;

    private UUID userId;
    private UserResponse userResponse;
    private final Date currentDate = new Date();

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setActive(Boolean.FALSE);
        userResponse.setLastLogin(currentDate);
        userResponse.setCreated(currentDate);
        userResponse.setModified(currentDate);
    }

    @Test
    void testExecute() {
        when(userApplicationService.findById(userId)).thenReturn(userResponse);

        UserResponse fetchedUser = getByIdUseCase.execute(userId);

        assertEquals(userId, fetchedUser.getId());
        assertEquals(Boolean.FALSE, fetchedUser.isActive());
        assertEquals(currentDate, fetchedUser.getLastLogin());
        assertEquals(currentDate, fetchedUser.getCreated());
        assertEquals(currentDate, fetchedUser.getModified());

        verify(userApplicationService, times(1)).findById(userId);
    }

}