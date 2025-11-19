package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigOpenAPI {

    @Bean
    public OpenAPI configDoc() {
        return new OpenAPI().info(new Info()
                .title("Modulo 6 - Proyecto de Eventos y Espacios")
                .version("2.0 Beta")
                .description("API REST Sobre Organización de Eventos y Espacios")
        );
    }
}
