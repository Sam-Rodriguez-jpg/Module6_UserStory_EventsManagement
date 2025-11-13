package com.example.demo.repositories.implement;

import com.example.demo.entities.VenueEntity;
import com.example.demo.repositories.interfaces.VenueRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VenueRepositoryImplement implements VenueRepositoryInterface {
    // Database
    private final List<VenueEntity> venuesList = new ArrayList<>();

    // ID Serial
    long idVenueAutoIncrement = 0;

    // Metodos
    // GET ALL
    @Override
    public List<VenueEntity> getAllVenues() {
        return venuesList;
    }

    // GET BY ID
    @Override
    public VenueEntity getVenueById(long id) {
        for (VenueEntity venue : venuesList) {
            if (venue.getIdVenue() == id) {
                return venue;
            }
        }
        return null;
    }

    // POST
    @Override
    public VenueEntity createVenue(VenueEntity venueEntity) {
        venueEntity.setIdVenue(++idVenueAutoIncrement);
        venuesList.add(venueEntity);
        return venueEntity;
    }

    // PUT
    @Override
    public VenueEntity updateVenue(long id, VenueEntity venueEntity) {
        for (VenueEntity venue : venuesList) {
            if (venue.getIdVenue() == id) {
                venue.setNameVenue(venueEntity.getNameVenue());
                venue.setAddressVenue(venueEntity.getAddressVenue());
                venue.setCityVenue(venueEntity.getCityVenue());
                venue.setCapacityVenue(venueEntity.getCapacityVenue());
                return venue;
            }
        }
        return null;
    }

    // PATCH
    @Override
    public VenueEntity partialUpdateVenue(long id, VenueEntity venueEntity) {
        for (VenueEntity venue : venuesList) {
            if (venue.getIdVenue() == id) {
                if (venueEntity.getNameVenue() != null) {
                    venue.setNameVenue(venueEntity.getNameVenue());
                }
                if (venueEntity.getAddressVenue() != null) {
                    venue.setAddressVenue(venueEntity.getAddressVenue());
                }
                if (venueEntity.getCityVenue() != null) {
                    venue.setCityVenue(venueEntity.getCityVenue());
                }
                if (venueEntity.getCapacityVenue() != null && venueEntity.getCapacityVenue() >= 0) {
                    venue.setCapacityVenue(venueEntity.getCapacityVenue());
                }
            }
        }
        return null;
    }

    // DELETE BY ID
    @Override
    public void deleteVenue(long id) {
        venuesList.removeIf(venue -> venue.getIdVenue() == id);
    }

    // DELETE ALL
    @Override
    public void deleteAllVenues() {
        venuesList.clear();
    }
}
