package com.example.demo.domain.ports.in.events;

import com.example.demo.domain.models.EventModel;

public interface UpdateEventUseCaseInterface {
    EventModel update(Long id, EventModel eventModel);
}
