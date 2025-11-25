package com.example.demo.domain.ports.in.events;

import com.example.demo.domain.models.EventModel;

import java.util.Optional;

public interface GetEventByIdUseCaseInterface {
    Optional<EventModel> findById(Long id);
}
