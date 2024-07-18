package com.management.users.infrastructure.mappers;

import com.management.users.application.dtos.requests.PhoneRequest;
import com.management.users.application.dtos.responses.PhoneResponse;
import com.management.users.application.mappers.GenericMapper;
import com.management.users.domain.entities.PhoneEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplPhoneMapper implements GenericMapper<PhoneRequest, PhoneResponse, PhoneEntity> {

    private final ModelMapper modelMapper;

    @Override
    public PhoneResponse toDto(PhoneEntity phoneEntity) {
        return modelMapper.map(phoneEntity, PhoneResponse.class);
    }

    @Override
    public PhoneEntity toEntity(PhoneRequest phoneRequest) {
        return modelMapper.map(phoneRequest, PhoneEntity.class);
    }

}