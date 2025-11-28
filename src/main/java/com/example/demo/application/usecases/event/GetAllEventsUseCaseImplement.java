package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.GetAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class GetAllEventsUseCaseImplement implements GetAllEventsUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public GetAllEventsUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public List<EventModel> findAll() {
        log.info("Fetching all events from DB");
        List<EventModel> events = eventRepositoryPort.findAll();
        log.info("Retrieved {} events", events.size());
        return events;
    }
}
