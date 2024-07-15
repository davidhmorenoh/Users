package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.requests.update.UserUpdateRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateUseCase {

    private final UserApplicationService userApplicationService;

    public UserResponse execute(UUID id, UserUpdateRequest userRequest) {
        return userApplicationService.updateUser(id, userRequest);
    }

}