package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.GetAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;

import java.util.List;

public class GetAllEventsUseCaseImplement implements GetAllEventsUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public GetAllEventsUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public List<EventModel> findAll() {
        return eventRepositoryPort.findAll();
    }
}
