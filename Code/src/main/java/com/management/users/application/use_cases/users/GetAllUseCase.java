package com.management.users.application.use_cases.users;

import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.services.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUseCase {

    private final UserApplicationService userApplicationService;

    public List<UserResponse> execute() {
        return userApplicationService.getAllUsers();
    }

}