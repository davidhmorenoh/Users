package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.requests.PhoneRequest;
import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.PhoneResponse;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUseCaseTest {

    @Mock
    private UserApplicationService userApplicationService;

    private UserRequest userRequest;
    private UserResponse userResponse;

    @InjectMocks
    private CreateUseCase createUseCase;

    private final Date currentDate = new Date();

    @BeforeEach
    void setUp() {
        PhoneRequest phoneRequest = new PhoneRequest(UUID.randomUUID(), "Celular", "1", "60");
        List<PhoneRequest> phoneRequests = new ArrayList<>();
        phoneRequests.add(phoneRequest);
        userRequest = new UserRequest("Test User", "test@user.com", "password", phoneRequests);

        PhoneResponse phoneResponse = new PhoneResponse(UUID.randomUUID(), "Celular", "1", "60");
        List<PhoneResponse> phoneResponses = new ArrayList<>();
        phoneResponses.add(phoneResponse);
        userResponse = new UserResponse(UUID.randomUUID(), currentDate, currentDate, currentDate, "Test User", false, phoneResponses);
    }

    @Test
    void testExecute() {
        when(userApplicationService.createUser(any(UserRequest.class))).thenReturn(userResponse);

        UserResponse createdUser = createUseCase.execute(userRequest);
        assertNotNull(createdUser);
        assertEquals(createdUser.getToken(), userResponse.getToken());
        assertEquals(createdUser.getPhones().size(), userResponse.getPhones().size());
        assertEquals(createdUser.getPhones().getFirst().getCityCode(), userResponse.getPhones().getFirst().getCityCode());
        assertEquals(createdUser.getPhones().getFirst().getCountryCode(), userResponse.getPhones().getFirst().getCountryCode());
        verify(userApplicationService, times(1)).createUser(userRequest);
    }

}