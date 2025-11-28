package com.example.demo.infrastructure.adapters.in.web.mappers;

import com.example.demo.domain.models.EventModel;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.EventDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.EventDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapperDto {

    // Dto Request a Domain Model
    EventModel toDomainModel(EventDtoRequest eventDtoRequest);

    // Domain Model a Dto Response
    EventDtoResponse toResponse(EventModel eventModel);
}
