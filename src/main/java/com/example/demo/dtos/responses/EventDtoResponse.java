package com.example.demo.dtos.responses;

import com.example.demo.domain.models.enums.StatusEventEnum;

import java.time.LocalDateTime;

public record EventDtoResponse(
   Long idEvent,
   String nameEvent,
   String descriptionEvent,
   LocalDateTime datetimeEvent,
   Integer durationEvent,
   Double priceEvent,
   StatusEventEnum statusEvent,
   Long idVenue
) {}
