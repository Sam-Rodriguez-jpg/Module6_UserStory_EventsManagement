package com.example.demo.infrastructure.adapters.out.jpa.mappers;

import com.example.demo.domain.models.EventModel;
import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import com.example.demo.infrastructure.adapters.out.jpa.entites.VenueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapperModel {

    @Mapping(target = "venueEntity", expression = "java(toVenueEntity(eventModel.idVenue()))")
    EventEntity toEntity(EventModel eventModel);

    @Mapping(target = "idVenue", source = "venueEntity.idVenue")
    EventModel toModel(EventEntity eventEntity);

    // Convertir un ID a un VenueEntity (solo con id)
    default VenueEntity toVenueEntity(Long idVenue) {
        if (idVenue == null) return null;

        VenueEntity venueEntity = new VenueEntity();
        venueEntity.setIdVenue(idVenue);
        return venueEntity;
    }
}
