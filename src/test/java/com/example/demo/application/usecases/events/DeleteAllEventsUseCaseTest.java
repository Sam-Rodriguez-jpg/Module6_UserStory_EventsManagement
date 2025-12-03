package com.example.demo.application.usecases.events;

import com.example.demo.application.usecases.event.DeleteAllEventsUseCaseImplement;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class DeleteAllEventsUseCaseTest {

    @Mock
    private EventRepositoryPort eventRepositoryPort;

    @InjectMocks
    private DeleteAllEventsUseCaseImplement deleteAllUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteAll() {
        deleteAllUseCase.deleteAll();
        verify(eventRepositoryPort, times(1)).deleteAll();
    }
}

