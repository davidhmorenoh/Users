package com.management.users.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OpenApiConfigTest {

    @Test
    public void testCustomOpenAPI() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OpenApiConfig.class);
        OpenAPI openAPI = context.getBean(OpenAPI.class);

        assertNotNull(openAPI);
        assertEquals("Users management API", openAPI.getInfo().getTitle());
        assertEquals("1.0", openAPI.getInfo().getVersion());
        assertEquals("API documentation for the users management application", openAPI.getInfo().getDescription());

        context.close();
    }

    @Test
    public void testPublicApi() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OpenApiConfig.class);
        GroupedOpenApi groupedOpenApi = context.getBean(GroupedOpenApi.class);

        assertNotNull(groupedOpenApi);
        assertEquals("public", groupedOpenApi.getGroup());
        assertTrue(groupedOpenApi.getPathsToMatch().contains("/api/**"));

        context.close();
    }

}