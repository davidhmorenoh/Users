package com.management.users.infrastructure.mappers;

import com.management.users.application.dtos.requests.UserRequest;
import com.management.users.application.dtos.responses.UserResponse;
import com.management.users.domain.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ImplUserMapperTest {

    private ImplUserMapper implUserMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        implUserMapper = new ImplUserMapper(modelMapper);
    }

    @Test
    public void testToDto() {
        // Given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setModified(new Date());

        // When
        UserResponse userResponse = implUserMapper.toDto(userEntity);

        // Then
        assertNotNull(userResponse);
        assertEquals(userEntity.getId(), userResponse.getId());
        assertEquals(userEntity.getModified(), userResponse.getModified());
    }

    @Test
    public void testToEntity() {
        // Given
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("NewPassword7@");

        // When
        UserEntity userEntity = implUserMapper.toEntity(userRequest);

        // Then
        assertNotNull(userEntity);
        assertEquals(userRequest.getEmail(), userEntity.getEmail());
        assertEquals(userRequest.getPassword(), userEntity.getPassword());
    }

}