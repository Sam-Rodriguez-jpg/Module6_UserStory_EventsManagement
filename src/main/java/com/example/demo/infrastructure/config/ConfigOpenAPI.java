package com.example.demo.infrastructure.config;

import com.example.demo.domain.models.enums.StatusEventEnum;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
                          "statusEvent": "Active",
                          "idVenue": 50
                        }
                        """
                );

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
                          "statusEvent": "Finalized",
                          "idVenue": 50
                        }
                        """
                );

        // Ejemplo para PATCH (actualización parcial)
        Example patchExample = new Example()
                .summary("Ejemplo de actualización parcial")
                .description("Solo campos modificados")
                .value("""
                        {
                          "descriptionEvent": "Actualización del evento con nueva información",
                          "priceEvent": 45.0
                        }
                        """
                );


        return new OpenAPI()
                .info(new Info()
                        .title("Modulo 6 - Proyecto de Eventos y Espacios")
                        .version("4.4 Beta")
                        .description("API REST Sobre Organización de Eventos y Espacios")
                )
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
                );
    }
}
