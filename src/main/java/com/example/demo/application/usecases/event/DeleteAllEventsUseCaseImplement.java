package com.example.demo.application.usecases.event;

import com.example.demo.domain.ports.in.events.DeleteAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;

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
