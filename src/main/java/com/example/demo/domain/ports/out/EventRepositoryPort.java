package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.EventModel;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    EventModel save(EventModel eventModel);
    Optional<EventModel> findById(Long id);
    List<EventModel> findAll();
    void deleteById(Long id);
    void deleteAll();
}
