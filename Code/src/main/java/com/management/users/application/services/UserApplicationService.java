package com.management.users.application.services;

import com.management.users.application.dtos.requests.creation.UserCreationRequest;
import com.management.users.application.dtos.requests.update.UserUpdateRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.UserEntity;
import com.management.users.domain.services.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserDomainService userDomainService;

    private final GenericMapper<UserCreationRequest, UserResponse, UserEntity> userCreationMapper;
    private final GenericMapper<UserUpdateRequest, UserResponse, UserEntity> userUpdateMapper;

    public List<UserResponse> getAllUsers() {
        return userDomainService.getAllUsers().stream().map(userCreationMapper::toDto).collect(Collectors.toList());
    }

    public UserResponse findById(UUID id) {
        UserEntity user = userDomainService.findById(id);
        return userCreationMapper.toDto(user);
    }

    public UserResponse createUser(UserCreationRequest userRequest) {
        UserEntity user = userCreationMapper.toEntity(userRequest);
        UserEntity userCreated = userDomainService.createUser(user);
        return userCreationMapper.toDto(userCreated);
    }

    public UserResponse updateUser(UUID id, UserUpdateRequest userRequest) {
        UserEntity user = userUpdateMapper.toEntity(userRequest);
        UserEntity userUpdated = userDomainService.updateUser(id, user);
        return userUpdateMapper.toDto(userUpdated);
    }

    public UserResponse enableUser(UUID id) {
        UserEntity enabledUser = userDomainService.enableUser(id);
        return userCreationMapper.toDto(enabledUser);
    }

    public UserResponse disableUser(UUID id) {
        UserEntity disabledUser = userDomainService.disableUser(id);
        return userCreationMapper.toDto(disabledUser);
    }

    public void deleteUser(UUID id) {
        userDomainService.deleteUser(id);
    }

}