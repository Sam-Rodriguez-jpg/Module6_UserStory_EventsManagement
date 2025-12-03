package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.CreateEventUseCaseImplement;
import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateEventUseCaseTest {

    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private CreateEventUseCaseImplement createEventUseCaseImplement;

    private EventModel eventModel;

    @BeforeEach
    void setUp() {

        eventModel = new EventModel(
                null, // null porque es creacion
                "Concierto de Ye",
                "Evento musical",
                null,
                120,
                50.0,
                null,
                90L
        );
    }

    @Test
    void testCreateEventSuccess() {
        // Preparar el mock
        EventModel savedEvent = new EventModel(
                1L,
                eventModel.nameEvent(),
                eventModel.descriptionEvent(),
                eventModel.datetimeEvent(),
                eventModel.durationEvent(),
                eventModel.priceEvent(),
                eventModel.statusEvent(),
                eventModel.idVenue()
        );
        when(eventRepositoryPort.save(eventModel)).thenReturn(savedEvent);

        // Ejecutar el use case
        EventModel result = createEventUseCaseImplement.create(eventModel);

        // Validar resultados
        assertNotNull(result);
        assertEquals(1L, result.idEvent());
        assertEquals("Concierto de Ye", result.nameEvent());

        // Verificar que el repositorio fue llamado
        verify(eventRepositoryPort, times(1)).save(eventModel);
    }
}
