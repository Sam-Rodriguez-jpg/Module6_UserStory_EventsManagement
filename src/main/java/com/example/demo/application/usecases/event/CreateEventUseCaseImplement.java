package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.CreateEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;

public class CreateEventUseCaseImplement implements CreateEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public CreateEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public EventModel create(EventModel eventModel) {
        return eventRepositoryPort.save(eventModel);
    }
}
