package com.management.users.application.services;

import com.management.users.application.dtos.requests.UserRequest;
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

    private final GenericMapper<UserRequest, UserResponse, UserEntity> userMapper;

    public List<UserResponse> getAllUsers() {
        return userDomainService.getAllUsers().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserResponse findById(UUID id) {
        UserEntity user = userDomainService.findById(id);
        return userMapper.toDto(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        UserEntity user = userMapper.toEntity(userRequest);
        UserEntity userCreated = userDomainService.createUser(user);
        return userMapper.toDto(userCreated);
    }

    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        UserEntity user = userMapper.toEntity(userRequest);
        UserEntity userUpdated = userDomainService.updateUser(id, user);
        return userMapper.toDto(userUpdated);
    }

    public UserResponse enableUser(UUID id) {
        UserEntity enabledUser = userDomainService.enableUser(id);
        return userMapper.toDto(enabledUser);
    }

    public UserResponse disableUser(UUID id) {
        UserEntity disabledUser = userDomainService.disableUser(id);
        return userMapper.toDto(disabledUser);
    }

    public void deleteUser(UUID id) {
        userDomainService.deleteUser(id);
    }

}