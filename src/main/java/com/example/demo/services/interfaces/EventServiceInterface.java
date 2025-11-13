package com.example.demo.services.interfaces;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;


import java.util.List;

public interface EventServiceInterface {
    List<EventDtoResponse> getAllEvents();
    EventDtoResponse getEventById(long id);
    EventDtoResponse createEvent(EventDtoRequest eventDtoRequest);
    EventDtoResponse updateEvent(long id, EventDtoRequest eventDtoRequest);
    EventDtoResponse partialUpdateEvent(long id, EventDtoRequest eventDtoRequest);
    void deleteEventById(long id);
    void deleteAllEvents();
}
