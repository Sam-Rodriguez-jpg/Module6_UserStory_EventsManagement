package com.example.demo.repositories.interfaces;

import com.example.demo.entities.VenueEntity;

import java.util.List;

public interface VenueRepositoryInterface {
    List<VenueEntity> getAllVenues();
    VenueEntity getVenueById(long id);
    VenueEntity createVenue(VenueEntity venueEntity);
    VenueEntity updateVenue(long id, VenueEntity venueEntity);
    VenueEntity partialUpdateVenue(long id, VenueEntity venueEntity);
    void deleteVenue(long id);
    void deleteAllVenues();
}
