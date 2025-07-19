package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Система оценки ресторанов API",
        version = "1.0",
        description = "REST API для управления посетителями, ресторанами и отзывами",
        contact = @Contact(
            name = "Разработчик",
            email = "dev@example.com"
        )
    )
)
public class SwaggerConfig {
    // Конфигурация Swagger
}
