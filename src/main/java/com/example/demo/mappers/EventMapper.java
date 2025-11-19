package com.example.demo.mappers;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "venueEntity", ignore = true)
    EventEntity toEntity(EventDtoRequest dto);

    @Mapping(target = "idVenue", source = "venueEntity.idVenue")
    EventDtoResponse toResponse(EventEntity entity);
}
