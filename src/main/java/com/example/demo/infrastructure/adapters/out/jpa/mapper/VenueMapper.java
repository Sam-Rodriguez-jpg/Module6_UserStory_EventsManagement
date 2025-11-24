package com.example.demo.infrastructure.adapters.out.jpa.mapper;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.VenueDtoResponse;
import com.example.demo.entities.VenueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);

    VenueEntity toEntity(VenueDtoRequest venueDtoRequest);

    @Mapping(target = "idVenue", source = "idVenue")
    VenueDtoResponse toResponse(VenueEntity venueEntity);
}

