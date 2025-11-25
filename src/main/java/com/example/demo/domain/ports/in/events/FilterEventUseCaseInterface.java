package com.example.demo.domain.ports.in.events;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface FilterEventUseCaseInterface {
    Page<EventModel> filter(
            StatusEventEnum statusEvent,
            Long idVenue,
            LocalDateTime dateEvent,
            String cityEvent,
            Pageable pageable
    );
}
