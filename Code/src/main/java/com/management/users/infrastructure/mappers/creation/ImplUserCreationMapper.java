package com.management.users.infrastructure.mappers.creation;

import com.management.users.application.dtos.requests.creation.UserCreationRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplUserCreationMapper implements GenericMapper<UserCreationRequest, UserResponse, UserEntity> {

    private final ModelMapper modelMapper;

    @Override
    public UserResponse toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public UserEntity toEntity(UserCreationRequest userRequest) {
        return modelMapper.map(userRequest, UserEntity.class);
    }

}