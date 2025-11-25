package com.example.demo.infrastructure.adapters.in.web.dtos.requests;

import com.example.demo.domain.models.enums.StatusEventEnum;

import java.time.LocalDateTime;

public record EventDtoRequest(
        Long idEvent,
        String nameEvent,
        String descriptionEvent,
        LocalDateTime datetimeEvent,
        Integer durationEvent,
        Double priceEvent,
        StatusEventEnum statusEvent,
        Long idVenue
) {}
