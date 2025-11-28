package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.in.events.PartialUpdateEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PartialUpdateEventUseCaseImplement implements PartialUpdateEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public PartialUpdateEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public EventModel partialUpdate(Long id, EventModel eventModel) {

        log.info("Attempting to partially update event with ID: {}", id);

        Optional<EventModel> optionalEventModel = eventRepositoryPort.findById(id);

        if (optionalEventModel.isEmpty()) {
            log.warn("Event with ID {} not found in database", id);
            throw new NotFoundException("No se encontró ningún evento con el ID: " + id);
        }

        EventModel eventModelExisting = optionalEventModel.get();

        // Log completo del estado actual
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

        // Log de los valores recibidos en el request
        log.info(
                "Values received for partial update: name={}, description={}, datetime={}, duration={}, price={}, status={}, idVenue={}",
                eventModel.nameEvent(),
                eventModel.descriptionEvent(),
                eventModel.datetimeEvent(),
                eventModel.durationEvent(),
                eventModel.priceEvent(),
                eventModel.statusEvent(),
                eventModel.idVenue()
        );

        String nameEvent = eventModel.nameEvent() != null ? eventModel.nameEvent() : eventModelExisting.nameEvent();
        String descriptionEvent = eventModel.descriptionEvent() != null ? eventModel.descriptionEvent() : eventModelExisting.descriptionEvent();
        LocalDateTime datetimeEvent = eventModel.datetimeEvent() != null ? eventModel.datetimeEvent() : eventModelExisting.datetimeEvent();
        Integer durationEvent = eventModel.durationEvent() != null ? eventModel.durationEvent() : eventModelExisting.durationEvent();
        Double priceEvent = eventModel.priceEvent() != null ? eventModel.priceEvent() : eventModelExisting.priceEvent();
        StatusEventEnum statusEvent = eventModel.statusEvent() != null ? eventModel.statusEvent() : eventModelExisting.statusEvent();
        Long idVenue = eventModel.idVenue() != null ? eventModel.idVenue() : eventModelExisting.idVenue();

        EventModel updatedEvent = new EventModel(
                id,
                nameEvent,
                descriptionEvent,
                datetimeEvent,
                durationEvent,
                priceEvent,
                statusEvent,
                idVenue
        );

        // Log final del objeto actualizado
        log.info(
                "Updated event data to be saved: id={}, name={}, description={}, datetime={}, duration={}, price={}, status={}, idVenue={}",
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
