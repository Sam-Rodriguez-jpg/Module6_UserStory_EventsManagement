package com.example.demo.application.usecases.event;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.in.events.FilterEventUseCaseInterface;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
public class FilterEventUseCaseImplement implements FilterEventUseCaseInterface {

    private final EventRepositoryPort eventRepositoryPort;

    public FilterEventUseCaseImplement(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Page<EventModel> filter(StatusEventEnum statusEvent, Long idVenue, LocalDateTime datetimeEvent, String cityEvent, Pageable pageable) {
        log.info("Filtering events with parameters: status={}, venue={}, date={}, city={}",
                statusEvent, idVenue, datetimeEvent, cityEvent);
        Page<EventModel> page = eventRepositoryPort.filter(
                statusEvent, idVenue, datetimeEvent, cityEvent, pageable
        );
        log.info("Filter returned {} elements", page.getTotalElements());
        return page;
    }
}
