package com.example.demo.domain.ports.in.events;

import com.example.demo.domain.models.EventModel;

public interface PartialUpdateEventUseCaseInterface {
    EventModel partialUpdate(Long id, EventModel eventModel);
}
