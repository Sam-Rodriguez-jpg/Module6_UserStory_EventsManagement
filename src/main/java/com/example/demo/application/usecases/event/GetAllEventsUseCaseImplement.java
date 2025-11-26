package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.GetAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
