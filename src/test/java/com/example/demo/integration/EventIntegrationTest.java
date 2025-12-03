package com.example.demo.integration;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class EventIntegrationTest extends BaseIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventRepositoryPort eventRepositoryPort;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VenueRepositoryPort venueRepositoryPort; // o tu adaptador JPA de venues

    private EventModel eventModel;
    private VenueModel venueModel;

    @BeforeEach
    void setUp() {
        eventRepositoryPort.deleteAll(); // Limpiar BD antes de cada test
        venueRepositoryPort.deleteAll();

        // Crear un venue de prueba
        venueModel = new VenueModel(
                null,
                "Venue de Prueba",
                "Calle Falsa 123",
                "Ciudad Prueba",
                500
        );
        venueModel = venueRepositoryPort.save(venueModel);

        eventModel = new EventModel(
                null,
                "Prueba de Integración",
                "Evento de Prueba de Integración",
                LocalDateTime.now().plusDays(1),
                120,
                99.0,
                StatusEventEnum.ACTIVE,
                venueModel.idVenue()
        );

        eventModel = eventRepositoryPort.save(eventModel);
    }

    @Test
    void testGetAllEvents() throws Exception {
        mockMvc.perform(get("/api/events")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nameEvent").value("Prueba de Integración"));
    }

    @Test
    void testCreateEvent() throws Exception {
        EventModel newEvent = new EventModel(
                null,
                "Nuevo Evento",
                "Descripción",
                LocalDateTime.now().plusDays(2),
                90,
                50.0,
                StatusEventEnum.ACTIVE,
                venueModel.idVenue()
        );

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEvent))
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameEvent").value("Nuevo Evento"));
    }
}
