package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.DeleteAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.exceptions.custom.NotFoundException;

import java.util.Optional;

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
