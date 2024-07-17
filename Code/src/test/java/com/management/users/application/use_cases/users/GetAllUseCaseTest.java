package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.services.UserApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllUseCaseTest {

    @Mock
    private UserApplicationService userApplicationService;

    @InjectMocks
    private GetAllUseCase getAllUseCase;

    private List<UserResponse> userResponses;

    private final Date currentDate = new Date();

    @BeforeEach
    void setUp() {
        userResponses = new ArrayList<>();
        UserResponse user1 = new UserResponse();
        user1.setId(UUID.randomUUID());
        user1.setActive(Boolean.TRUE);
        user1.setLastLogin(currentDate);
        user1.setCreated(currentDate);
        user1.setModified(currentDate);
        UserResponse user2 = new UserResponse();
        user2.setId(UUID.randomUUID());
        user2.setActive(Boolean.TRUE);
        user2.setLastLogin(currentDate);
        user2.setCreated(currentDate);
        user2.setModified(currentDate);
        userResponses.add(user1);
        userResponses.add(user2);
    }

    @Test
    void testExecute() {
        when(userApplicationService.getAllUsers()).thenReturn(userResponses);

        List<UserResponse> fetchedUsers = getAllUseCase.execute();

        assertEquals(userResponses.size(), fetchedUsers.size());
        for (int i = 0; i < userResponses.size(); i++) {
            assertEquals(userResponses.get(i).getId(), fetchedUsers.get(i).getId());
            assertEquals(userResponses.get(i).isActive(), fetchedUsers.get(i).isActive());
            assertEquals(userResponses.get(i).getLastLogin(), fetchedUsers.get(i).getLastLogin());
            assertEquals(userResponses.get(i).getCreated(), fetchedUsers.get(i).getCreated());
            assertEquals(userResponses.get(i).getModified(), fetchedUsers.get(i).getModified());
        }

        verify(userApplicationService, times(1)).getAllUsers();
    }

}