package com.example.demo.mappers;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.VenueDtoResponse;
import com.example.demo.entities.VenueEntity;

// Con esta clase se convertirá de Entity a Dto y viceversa
public class VenueMapper {

    // De Entity a DtoResponse (Para mostrar)
    public VenueDtoResponse toResponse(VenueEntity venueEntity) {
        if (venueEntity == null) return null;

        return new VenueDtoResponse(
                venueEntity.getIdVenue(),
                venueEntity.getNameVenue(),
                venueEntity.getAddressVenue(),
                venueEntity.getCityVenue(),
                venueEntity.getCapacityVenue()
        );
    }

    // De Dto a Entity (Para crear)
    public VenueEntity toEntity(VenueDtoRequest venueDtoRequest) {
        if (venueDtoRequest == null) return null;

        // Si lo que pasamos por postman o el front no es nulo, creamos una nueva entidad con la información que se pasa por la petición (request)
        VenueEntity venueEntity = new VenueEntity();
        venueEntity.setNameVenue(venueDtoRequest.nameVenue());
        venueEntity.setAddressVenue(venueDtoRequest.addressVenue());
        venueEntity.setCityVenue(venueDtoRequest.cityVenue());
        venueEntity.setCapacityVenue(venueDtoRequest.capacityVenue());

        return venueEntity;
    }
}
