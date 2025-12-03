package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.GetEventByIdUseCaseImplement;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetEventByIdUseCaseTest {

    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private GetEventByIdUseCaseImplement getEventByIdUseCase;

    private EventModel eventModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        eventModel = new EventModel(1L, "Evento 1", "Desc", null, 60, 20.0, null, 1L);
    }

    @Test
    void testFindByIdSuccess() {
        when(eventRepositoryPort.findById(1L)).thenReturn(Optional.of(eventModel));
    }

    @Test
    void testFindByIdNotFound() {
        when(eventRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> getEventByIdUseCase.findById(2L));
        verify(eventRepositoryPort, times(1)).findById(2L);
    }
}
