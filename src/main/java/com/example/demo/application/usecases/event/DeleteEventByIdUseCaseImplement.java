package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.DeleteEventByIdUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DeleteEventByIdUseCaseImplement implements DeleteEventByIdUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public DeleteEventByIdUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void deleteById(Long id) {
        Optional<EventModel> optionalEventModel = eventRepositoryPort.findById(id);

        if (optionalEventModel.isEmpty()) {
            throw new NotFoundException("No se encontró ningún evento con el ID: " + id);
        }

        eventRepositoryPort.deleteById(id);
    }
}
