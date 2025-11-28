package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.GetEventByIdUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class GetEventByIdUseCaseImplement implements GetEventByIdUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public GetEventByIdUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public EventModel findById(Long id) {
        log.info("Fetching event by ID: {}", id);

        Optional<EventModel> optionalEventModel = eventRepositoryPort.findById(id);

        if (optionalEventModel.isEmpty()) {
            log.warn("Event with ID {} not found", id);
            throw new NotFoundException("Evento no encontrado con el ID: " + id);
        }

        EventModel eventModel = optionalEventModel.get();
        log.info("Event found: {}", eventModel.nameEvent());

        return eventModel;
    }
}
