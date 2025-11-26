package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.in.events.PartialUpdateEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.infrastructure.adapters.in.web.exceptions.custom.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class PartialUpdateEventUseCaseImplement implements PartialUpdateEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public PartialUpdateEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public EventModel partialUpdate(Long id, EventModel eventModel) {

        Optional<EventModel> optionalEventModel = eventRepositoryPort.findById(id);

        if (optionalEventModel.isEmpty()) {
            throw new NotFoundException("No se encontró ningún evento con el ID: " + id);
        }

        EventModel eventModelExisting = optionalEventModel.get();

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

        return eventRepositoryPort.save(updatedEvent);
    }
}
