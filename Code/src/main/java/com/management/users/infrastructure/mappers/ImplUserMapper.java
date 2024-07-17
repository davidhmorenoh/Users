package com.management.users.infrastructure.mappers;

import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplUserMapper implements GenericMapper<UserRequest, UserResponse, UserEntity> {

    private final ModelMapper modelMapper;

    @Override
    public UserResponse toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public UserEntity toEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserEntity.class);
    }

}