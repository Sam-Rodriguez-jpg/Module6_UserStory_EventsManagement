package com.example.demo.infrastructure.config.doc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT Authorization header using the Bearer scheme.\n\n" +
                "Format: **Bearer {token}**",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class ConfigOpenAPI {

    @Bean
    public OpenAPI configDoc() {

        // Ejemplo para POST (crear evento)
        Example postExample = new Example()
                .summary("Ejemplo de creación de evento")
                .description("Payload recomendado para crear un nuevo evento")
                .value("""
                        {
                          "nameEvent": "Concierto de Rock",
                          "descriptionEvent": "Evento con banda en vivo",
                          "datetimeEvent": "2025-12-31T20:00:00",
                          "durationEvent": 120,
                          "priceEvent": 50.0,
                          "statusEvent": "ACTIVE",
                          "idVenue": 50
                        }
                        """);

        // Ejemplo para PUT (actualización completa)
        Example putExample = new Example()
                .summary("Ejemplo de actualización completa")
                .description("Payload completo para actualizar todos los datos del evento")
                .value("""
                        {
                          "nameEvent": "Concierto de Jazz",
                          "descriptionEvent": "Show con banda de jazz",
                          "datetimeEvent": "2026-01-15T19:30:00",
                          "durationEvent": 90,
                          "priceEvent": 40.0,
                          "statusEvent": "FINALIZED",
                          "idVenue": 50
                        }
                        """);

        // Ejemplo para PATCH (actualización parcial)
        Example patchExample = new Example()
                .summary("Ejemplo de actualización parcial")
                .description("Solo campos modificados")
                .value("""
                        {
                          "descriptionEvent": "Actualización del evento con nueva información",
                          "priceEvent": 45.0
                        }
                        """);

        // Ejemplo para RESISTER & LOGIN de Users
        // Ejemplo de registro
        Example registerExample = new Example()
                .summary("Ejemplo de registro de usuario")
                .description("Payload para registrar un nuevo usuario")
                .value("""
                    {
                      "username": "usuarioExample",
                      "password": "password123",
                      "role": "USER"
                    }
                    """);

        // Ejemplo de login
        Example loginExample = new Example()
                .summary("Ejemplo de login")
                .description("Payload para autenticarse y recibir JWT")
                .value("""
                    {
                      "username": "usuarioExample",
                      "password": "password123"
                    }
                    """);

        // Configuración global de OpenAPI con seguridad y ejemplos
        return new OpenAPI()
                .info(new Info()
                        .title("Modulo 6 - Proyecto de Eventos y Espacios")
                        .version("5.3 Beta")
                        .description("API REST Sobre Organización de Eventos y Espacios"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth")) // ⚡ agrega la seguridad global
                .components(new Components()
                        .addRequestBodies("EventDtoRequestPost",
                                new io.swagger.v3.oas.models.parameters.RequestBody()
                                        .description("Cuerpo para crear un evento")
                                        .content(new Content().addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/EventDtoRequest"))
                                                        .addExamples("postExample", postExample)
                                        ))
                        )
                        .addRequestBodies("EventDtoRequestPut",
                                new io.swagger.v3.oas.models.parameters.RequestBody()
                                        .description("Cuerpo para actualizar completamente un evento")
                                        .content(new Content().addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/EventDtoRequest"))
                                                        .addExamples("putExample", putExample)
                                        ))
                        )
                        .addRequestBodies("EventDtoRequestPatch",
                                new io.swagger.v3.oas.models.parameters.RequestBody()
                                        .description("Cuerpo para actualización parcial")
                                        .content(new Content().addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/EventDtoRequest"))
                                                        .addExamples("patchExample", patchExample)
                                        ))
                        )
                        .addRequestBodies("UserRegisterDtoRequestBody",
                                new io.swagger.v3.oas.models.parameters.RequestBody()
                                        .description("Cuerpo para registrar un usuario")
                                        .content(new Content().addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/UserRegisterDtoRequest"))
                                                        .addExamples("registerExample", registerExample)
                                        ))
                        )
                        .addRequestBodies("UserLoginDtoRequestBody",
                                new io.swagger.v3.oas.models.parameters.RequestBody()
                                        .description("Cuerpo para login")
                                        .content(new Content().addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/UserLoginDtoRequest"))
                                                        .addExamples("loginExample", loginExample)
                                        ))
                        )
                );
    }
}
