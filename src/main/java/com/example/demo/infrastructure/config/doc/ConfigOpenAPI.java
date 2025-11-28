package com.example.demo.infrastructure.config.doc;

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
                .summary("Ejemplo de creación de evento") // Titulo del ejemplo
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
                ); // JSON que recomendara usar

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

        // Este objeto define la configuración global de Swagger
        return new OpenAPI()
                .info(new Info() // Información de la API
                        .title("Modulo 6 - Proyecto de Eventos y Espacios") // Título de la API
                        .version("4.4 Beta") // Version de la API
                        .description("API REST Sobre Organización de Eventos y Espacios")
                )
                // Define los RequestBodies para cada operacion
                .components(new Components()
                        .addRequestBodies("EventDtoRequestPost", // RequestBody
                                new io.swagger.v3.oas.models.parameters.RequestBody()
                                        .description("Cuerpo para crear un evento") // Descripción del RequestBody
                                        .content(new Content().addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/EventDtoRequest")) // Referencia a la cual apunta el schema
                                                        .addExamples("postExample", postExample) // Asocia el ejemplo creado
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
