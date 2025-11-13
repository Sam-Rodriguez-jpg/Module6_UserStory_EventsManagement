package com.example.demo.repositories.implement;

import com.example.demo.entities.EventEntity;
import com.example.demo.repositories.interfaces.EventRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepositoryImplement implements EventRepositoryInterface {
    // Database
    private final List<EventEntity> eventsList = new ArrayList<>();

    // ID Serial
    long idEventAutoIncrement = 0;

    // Metodos
    // GET ALL
    @Override
    public List<EventEntity> getAllEvents() {
        return eventsList;
    }

    // GET BY ID
    @Override
    public EventEntity getEventById(long id) {
        for (EventEntity event : eventsList) {
            if (event.getIdEvent() == id) {
                return event;
            }
        }
        return null;
    }

    // POST
    @Override
    public EventEntity createEvent(EventEntity eventEntity) {
        eventEntity.setIdEvent(++idEventAutoIncrement);
        eventsList.add(eventEntity);
        return eventEntity;
    }

    // PUT
    @Override
    public EventEntity updateEvent(long id, EventEntity eventEntity) {
        for (EventEntity event : eventsList) {
            if (event.getIdEvent() == id) {
                event.setNameEvent(eventEntity.getNameEvent());
                event.setDescriptionEvent(eventEntity.getDescriptionEvent());
                event.setDatetimeEvent(eventEntity.getDatetimeEvent());
                event.setDurationEvent(eventEntity.getDurationEvent());
                event.setPriceEvent(eventEntity.getPriceEvent());
                event.setStatusEvent(eventEntity.getStatusEvent());
                event.setIdEvent(eventEntity.getIdEvent());
                return event;
            }
        }
        return null;
    }

    // PATCH
    @Override
    public EventEntity partialUpdateEvent(long id, EventEntity eventEntity) {
        for (EventEntity event : eventsList) {
            if (event.getIdVenue() == id) {
                if (event.getNameEvent() != null) event.setNameEvent(eventEntity.getNameEvent());
                if (event.getDescriptionEvent() != null) event.setDescriptionEvent(eventEntity.getDescriptionEvent());
                if (event.getDatetimeEvent() != null) event.setDatetimeEvent(eventEntity.getDatetimeEvent());
                if (event.getDurationEvent() != null && event.getDurationEvent() > 0) event.setDurationEvent(eventEntity.getDurationEvent());
                if (event.getPriceEvent() != null && event.getPriceEvent() >= 0)event.setPriceEvent(eventEntity.getPriceEvent());
                if (event.getStatusEvent() != null) event.setStatusEvent(eventEntity.getStatusEvent());
                if (event.getIdVenue() != null && event.getIdVenue() > 0) event.setIdEvent(eventEntity.getIdEvent());
                return event;
            }
        }
        return null;
    }

    // DELETE BY ID
    @Override
    public void deleteEvent(long id) {
        eventsList.removeIf(event -> event.getIdVenue() == id);
    }

    // DELETE ALL
    @Override
    public void deleteAllEvents() {
        eventsList.clear();
    }
}
