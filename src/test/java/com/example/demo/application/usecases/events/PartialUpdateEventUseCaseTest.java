package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.PartialUpdateEventUseCaseImplement;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
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

public class PartialUpdateEventUseCaseTest {
    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private PartialUpdateEventUseCaseImplement partialUpdateUseCase;

    private EventModel existingEvent;
    private EventModel partialUpdate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingEvent = new EventModel(1L, "Old Name", "Old Desc", null, 60, 20.0, StatusEventEnum.ACTIVE, 1L);
        partialUpdate = new EventModel(null, "New Name", null, null, null, null, null, null);
    }

    @Test
    void testPartialUpdateSuccess() {
        when(eventRepositoryPort.findById(1L)).thenReturn(Optional.of(existingEvent));
        when(eventRepositoryPort.save(any(EventModel.class))).thenAnswer(i -> i.getArgument(0));

        EventModel result = partialUpdateUseCase.partialUpdate(1L, partialUpdate);

        assertEquals("New Name", result.nameEvent());
        assertEquals("Old Desc", result.descriptionEvent()); // campos no actualizados permanecen
        verify(eventRepositoryPort, times(1)).findById(1L);
        verify(eventRepositoryPort, times(1)).save(any(EventModel.class));
    }

    @Test
    void testPartialUpdateNotFound() {
        when(eventRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> partialUpdateUseCase.partialUpdate(2L, partialUpdate));
        verify(eventRepositoryPort, times(1)).findById(2L);
        verify(eventRepositoryPort, never()).save(any());
    }

}
