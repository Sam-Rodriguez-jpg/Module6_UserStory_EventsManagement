package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.UpdateEventUseCaseImplement;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateEventUseCaseTest {
    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private UpdateEventUseCaseImplement updateEventUseCase;

    private EventModel existingEvent;
    private EventModel updatedEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingEvent = new EventModel(1L, "Old Name", "Old Desc", null, 60, 20.0, null, 1L);
        updatedEvent = new EventModel(1L, "New Name", "New Desc", null, 90, 30.0, null, 1L);
    }

    @Test
    void testUpdateSuccess() {
        when(eventRepositoryPort.findById(1L)).thenReturn(Optional.of(existingEvent));
        when(eventRepositoryPort.save(any(EventModel.class))).thenReturn(updatedEvent);

        EventModel result = updateEventUseCase.update(1L, updatedEvent);

        assertEquals("New Name", result.nameEvent());
        assertEquals(90, result.durationEvent());
        verify(eventRepositoryPort, times(1)).findById(1L);
        verify(eventRepositoryPort, times(1)).save(any(EventModel.class));
    }

    @Test
    void testUpdateNotFound() {
        when(eventRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> updateEventUseCase.update(2L, updatedEvent));
        verify(eventRepositoryPort, times(1)).findById(2L);
        verify(eventRepositoryPort, never()).save(any());
    }
}
