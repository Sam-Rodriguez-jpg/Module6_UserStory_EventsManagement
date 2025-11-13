package com.example.demo.repositories.interfaces;

import com.example.demo.entities.EventEntity;

import java.util.List;

public interface EventRepositoryInterface {
    List<EventEntity> getAllEvents();
    EventEntity getEventById(long id);
    EventEntity createEvent(EventEntity eventEntity);
    EventEntity updateEvent(long id, EventEntity eventEntity);
    EventEntity partialUpdateEvent(long id, EventEntity eventEntity);
    void deleteEvent(long id);
    void deleteAllEvents();
}
