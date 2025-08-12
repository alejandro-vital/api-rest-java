package com.avital.api.rest.java.example.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Rest Java Example")
                        .description("Ejemplo sencillo de un servicio API Rest para búsqueda de personas por número telefónico")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("avital")));
    }
}
