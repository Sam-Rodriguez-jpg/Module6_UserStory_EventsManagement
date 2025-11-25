package com.example.demo.infrastructure.adapters.out.jpa.mappers;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.infrastructure.adapters.out.jpa.entites.VenueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapperModel {

    VenueEntity toEntity(VenueModel venueModel);

    VenueModel toModel(VenueEntity venueEntity);
}
