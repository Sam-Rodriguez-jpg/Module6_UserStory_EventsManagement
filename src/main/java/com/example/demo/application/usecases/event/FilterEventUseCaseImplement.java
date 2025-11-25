package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.in.events.FilterEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FilterEventUseCaseImplement implements FilterEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public FilterEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Page<EventModel> filter(StatusEventEnum statusEvent, Long idVenue, LocalDateTime dateEvent, String cityEvent, Pageable pageable) {
        return eventRepositoryPort.filter(statusEvent, idVenue, dateEvent, cityEvent, pageable);
    }
}
