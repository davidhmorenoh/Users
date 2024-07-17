package com.management.users.infrastructure.mappers;

import com.management.users.application.dtos.requests.PhoneRequest;
import com.management.users.application.dtos.responses.PhoneResponse;
import com.management.users.domain.entities.PhoneEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ImplPhoneMapperTest {

    private ImplPhoneMapper phoneMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        phoneMapper = new ImplPhoneMapper(modelMapper);
    }

    @Test
    public void testToDto() {
        // Given
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(UUID.randomUUID());
        phoneEntity.setNumber("123456789");

        // When
        PhoneResponse phoneResponse = phoneMapper.toDto(phoneEntity);

        // Then
        assertEquals(phoneEntity.getId(), phoneResponse.getId());
        assertEquals(phoneEntity.getNumber(), phoneResponse.getNumber());
    }

    @Test
    public void testToEntity() {
        // Given
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber("987654321");

        // When
        PhoneEntity phoneEntity = phoneMapper.toEntity(phoneRequest);

        // Then
        assertEquals(phoneRequest.getNumber(), phoneEntity.getNumber());
    }

}