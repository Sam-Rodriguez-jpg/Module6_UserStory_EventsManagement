package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.DeleteEventByIdUseCaseImplement;
import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteEventByIdUseCaseTest {

    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private DeleteEventByIdUseCaseImplement deleteUseCase;

    private EventModel existingEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingEvent = new EventModel(1L, "Evento", "Desc", null, 60, 20.0, null, 1L);
    }

    @Test
    void testDeleteSuccess() {
        when(eventRepositoryPort.findById(1L)).thenReturn(Optional.of(existingEvent));

        deleteUseCase.deleteById(1L);

        verify(eventRepositoryPort, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(eventRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> deleteUseCase.deleteById(2L));
        verify(eventRepositoryPort, never()).deleteById(any());
    }
}
