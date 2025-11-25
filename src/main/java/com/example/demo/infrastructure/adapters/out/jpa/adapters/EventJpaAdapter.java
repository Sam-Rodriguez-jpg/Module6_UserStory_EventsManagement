package com.example.demo.infrastructure.adapters.out.jpa.adapters;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import com.example.demo.infrastructure.adapters.out.jpa.mappers.EventMapperModel;
import com.example.demo.infrastructure.adapters.out.jpa.repositories.EventJpaRepository;
import com.example.demo.infrastructure.adapters.out.jpa.specifications.EventSpecificationsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventJpaRepository eventJpaRepository;
    private final EventMapperModel eventMapperModel;

    public EventJpaAdapter(EventJpaRepository eventJpaRepository, EventMapperModel eventMapperModel) {
        this.eventJpaRepository = eventJpaRepository;
        this.eventMapperModel = eventMapperModel;
    }

    // SAVE
    @Override
    public EventModel save(EventModel eventModel) {
        EventEntity eventEntity = eventMapperModel.toEntity(eventModel);
        EventEntity savedEvent = eventJpaRepository.save(eventEntity);
        return eventMapperModel.toModel(savedEvent);
    }

    // FIND BY ID
    @Override
    public Optional<EventModel> findById(Long id) {
        Optional<EventEntity> optionalEventEntity = eventJpaRepository.findById(id);
        if (optionalEventEntity.isEmpty()) return Optional.empty();

        EventModel eventModel = eventMapperModel.toModel(optionalEventEntity.get());
        return Optional.of(eventModel);
    }

    // FIND ALL
    @Override
    public List<EventModel> findAll() {
        List<EventEntity> eventEntityList = eventJpaRepository.findAll();
        List<EventModel> eventModelList = new ArrayList<>();

        for (EventEntity eventEntity : eventEntityList) {
            eventModelList.add(eventMapperModel.toModel(eventEntity));
        }

        return eventModelList;
    }

    // DELETE BY ID
    @Override
    public void deleteById(Long id) {
        eventJpaRepository.deleteById(id);
    }

    // DELETE ALL
    @Override
    public void deleteAll() {
        eventJpaRepository.deleteAll();;
    }



    // FILTROS
    public Page<EventModel> filter(
            StatusEventEnum statusEvent,
            Long idVenue,
            LocalDateTime datetimeEvent,
            String cityEvent,
            Pageable pageable
    ) {
        Specification<EventEntity> specification =
                EventSpecificationsBuilder.build(statusEvent, idVenue, datetimeEvent, cityEvent);

        Page<EventEntity> page = eventJpaRepository.findAll(specification, pageable);

        List<EventModel> result = new ArrayList<>();
        for (EventEntity eventEntity : page.getContent()) {
            result.add(eventMapperModel.toModel(eventEntity));

        }

        return new PageImpl<>(result, pageable, page.getTotalElements());
    }
}
