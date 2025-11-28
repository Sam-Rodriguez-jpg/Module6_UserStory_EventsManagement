package com.example.demo.application.usecases.event;

import com.example.demo.domain.ports.in.events.DeleteAllEventsUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DeleteAllEventsUseCaseImplement implements DeleteAllEventsUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public DeleteAllEventsUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void deleteAll() {
        log.info("Deleting ALL events from DB");
        eventRepositoryPort.deleteAll();
        log.info("All events deleted successfully");
    }
}
