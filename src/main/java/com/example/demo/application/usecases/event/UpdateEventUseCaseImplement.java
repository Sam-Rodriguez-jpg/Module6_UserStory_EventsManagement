package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.UpdateEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdateEventUseCaseImplement implements UpdateEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public UpdateEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public EventModel update(Long id, EventModel eventModel) {
        Optional<EventModel> optionalEventModel = eventRepositoryPort.findById(id);

        if (optionalEventModel.isEmpty()) {
            throw new NotFoundException("No se encontró ningún evento con el ID: " + id);
        }

        EventModel updatedEvent = new EventModel(
                id,
                eventModel.nameEvent(),
                eventModel.descriptionEvent(),
                eventModel.datetimeEvent(),
                eventModel.durationEvent(),
                eventModel.priceEvent(),
                eventModel.statusEvent(),
                eventModel.idVenue()
        );

        return eventRepositoryPort.save(updatedEvent);
    }
}
