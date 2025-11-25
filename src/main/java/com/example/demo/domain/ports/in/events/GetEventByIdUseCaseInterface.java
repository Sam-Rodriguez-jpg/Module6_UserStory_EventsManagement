package com.example.demo.domain.ports.in.events;

import com.example.demo.domain.models.EventModel;

import java.util.Optional;

public interface GetEventByIdUseCaseInterface {
    EventModel findById(Long id);
}
