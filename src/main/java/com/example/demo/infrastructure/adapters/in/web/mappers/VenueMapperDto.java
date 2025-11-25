package com.example.demo.infrastructure.adapters.in.web.mappers;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.VenueDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.VenueDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapperDto {

    // Dto Request a Domain Model
    VenueModel toDomainModel(VenueDtoRequest venueDtoRequest);

    // Domain Model a Dto Response
    VenueDtoResponse toResponse(VenueModel venueModel);
}

