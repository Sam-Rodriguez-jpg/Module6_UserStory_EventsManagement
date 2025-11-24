package com.example.demo.domain.ports.in.events;

import com.example.demo.domain.models.EventModel;

import java.util.List;

public interface GetAllEventsUseCaseInterface {
    List<EventModel> findAll();
}
