package com.management.users.infrastructure.mappers.update;

import com.management.users.application.dtos.requests.update.UserUpdateRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplUserUpdateMapper implements GenericMapper<UserUpdateRequest, UserResponse, UserEntity> {

    private final ModelMapper modelMapper;

    @Override
    public UserResponse toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public UserEntity toEntity(UserUpdateRequest userRequest) {
        return modelMapper.map(userRequest, UserEntity.class);
    }

}