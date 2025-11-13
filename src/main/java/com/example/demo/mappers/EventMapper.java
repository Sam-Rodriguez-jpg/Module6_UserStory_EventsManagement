package com.example.demo.mappers;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.entities.EventEntity;

public class EventMapper {

    // De Entity a DtoResponse (Visualizar)
    public EventDtoResponse toResponse(EventEntity eventEntity) {
        if (eventEntity == null) return null;

        return new EventDtoResponse(
                eventEntity.getIdEvent(),
                eventEntity.getNameEvent(),
                eventEntity.getDescriptionEvent(),
                eventEntity.getDatetimeEvent(),
                eventEntity.getDurationEvent(),
                eventEntity.getPriceEvent(),
                eventEntity.getStatusEvent(),
                eventEntity.getIdVenue()
        );
    }

    // De DtoRequest a Entity (Crear)
    public EventEntity toEntity(EventDtoRequest eventDtoRequest) {
        if (eventDtoRequest == null) return null;

        EventEntity eventEntity = new EventEntity();
        eventEntity.setNameEvent(eventDtoRequest.nameEvent());
        eventEntity.setDescriptionEvent(eventDtoRequest.descriptionEvent());
        eventEntity.setDatetimeEvent(eventDtoRequest.datetimeEvent());
        eventEntity.setDurationEvent(eventDtoRequest.durationEvent());
        eventEntity.setPriceEvent(eventDtoRequest.priceEvent());
        eventEntity.setStatusEvent(eventDtoRequest.statusEvent());
        eventEntity.setIdEvent(eventDtoRequest.idVenue());

        return eventEntity;
    }
}
