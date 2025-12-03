package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.GetAllEventsUseCaseImplement;
import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllEventsUseCaseTest {
    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private GetAllEventsUseCaseImplement getAllEventsUseCaseImplement;

    private List<EventModel> eventModelList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        eventModelList = new ArrayList<>();
        eventModelList.add(new EventModel(1L, "Evento 1", "Desc 1", null, 60, 20.0, null, 1L));
        eventModelList.add(new EventModel(2L, "Evento 2", "Desc 2", null, 90, 30.0, null, 2L));
    }

    @Test
    void testFindAllEvents() {
        // Mockear el repositorio
        when(eventRepositoryPort.findAll()).thenReturn(eventModelList);

        // Ejecutar el use case
        List<EventModel> result = getAllEventsUseCaseImplement.findAll();

        // Verificaciones
        assertEquals(2, result.size());
        assertEquals("Evento 1", result.get(0).nameEvent());
        verify(eventRepositoryPort, times(1)).findAll();
    }
}
