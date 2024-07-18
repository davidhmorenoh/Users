package com.management.users.infrastructure.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class MapperConfigTest {

    @Test
    public void testModelMapperBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MapperConfig.class);
        ModelMapper modelMapper = context.getBean(ModelMapper.class);

        assertNotNull(modelMapper);
        context.close();
    }

}