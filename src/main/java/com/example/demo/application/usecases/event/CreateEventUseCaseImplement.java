package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.CreateEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
