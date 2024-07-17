package com.management.users.application.use_cases.users;

import com.management.users.application.services.UserApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUseCaseTest {

    @Mock
    private UserApplicationService userApplicationService;

    @InjectMocks
    private DeleteUseCase deleteUseCase;

    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
    }

    @Test
    void testExecute() {
        doNothing().when(userApplicationService).deleteUser(userId);

        deleteUseCase.execute(userId);

        verify(userApplicationService, times(1)).deleteUser(userId);
    }

}