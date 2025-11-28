package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.ports.in.events.UpdateEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UpdateEventUseCaseImplement implements UpdateEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public UpdateEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public EventModel update(Long id, EventModel eventModel) {
        log.info("Updating event with ID: {}", id);

        Optional<EventModel> optionalEventModel = eventRepositoryPort.findById(id);

        if (optionalEventModel.isEmpty()) {
            log.warn("Event with ID {} not found for update", id);
            throw new NotFoundException("No se encontró ningún evento con el ID: " + id);
        }
        EventModel eventModelExisting = optionalEventModel.get();

        log.info(
                "Current event data: idEvent={}, name={}, description={}, datetime={}, duration={}, price={}, status={}, idVenue={}",
                eventModelExisting.idEvent(),
                eventModelExisting.nameEvent(),
                eventModelExisting.descriptionEvent(),
                eventModelExisting.datetimeEvent(),
                eventModelExisting.durationEvent(),
                eventModelExisting.priceEvent(),
                eventModelExisting.statusEvent(),
                eventModelExisting.idVenue()
        );

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

        log.info(
                "Updated event data to be saved: idEvent={}, name={}, description={}, datetime={}, duration={}, price={}, status={}, idVenue={}",
                updatedEvent.idEvent(),
                updatedEvent.nameEvent(),
                updatedEvent.descriptionEvent(),
                updatedEvent.datetimeEvent(),
                updatedEvent.durationEvent(),
                updatedEvent.priceEvent(),
                updatedEvent.statusEvent(),
                updatedEvent.idVenue()
        );

        return eventRepositoryPort.save(updatedEvent);
    }
}
