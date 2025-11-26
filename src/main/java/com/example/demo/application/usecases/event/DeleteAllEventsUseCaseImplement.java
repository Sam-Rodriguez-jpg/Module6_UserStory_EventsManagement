package com.example.demo.application.usecases.event;

import com.example.demo.domain.ports.in.events.DeleteAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteAllEventsUseCaseImplement implements DeleteAllEventsUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public DeleteAllEventsUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void deleteAll() {
        eventRepositoryPort.deleteAll();
    }
}
