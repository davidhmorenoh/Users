package com.management.users.infrastructure.mappers.creation;

import com.management.users.application.dtos.requests.creation.PhoneCreationRequest;
import com.management.users.application.dtos.responses.PhoneResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.PhoneEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplPhoneCreationMapper implements GenericMapper<PhoneCreationRequest, PhoneResponse, PhoneEntity> {

    private final ModelMapper modelMapper;

    @Override
    public PhoneResponse toDto(PhoneEntity phoneEntity) {
        return modelMapper.map(phoneEntity, PhoneResponse.class);
    }

    @Override
    public PhoneEntity toEntity(PhoneCreationRequest phoneRequest) {
        return modelMapper.map(phoneRequest, PhoneEntity.class);
    }

}