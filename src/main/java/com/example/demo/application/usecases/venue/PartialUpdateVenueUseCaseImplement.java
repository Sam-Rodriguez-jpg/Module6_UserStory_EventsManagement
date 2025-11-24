package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.PartialUpdateVenueUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import com.example.demo.exceptions.custom.NotFoundException;

import java.util.Optional;

public class PartialUpdateVenueUseCaseImplement implements PartialUpdateVenueUseCaseInterface {

    private final VenueRepositoryPort venueRepositoryPort;

    public PartialUpdateVenueUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public VenueModel partialUpdate(Long id, VenueModel venueModel) {
        Optional<VenueModel> optionalVenueModel = venueRepositoryPort.findById(id);

        if (optionalVenueModel.isEmpty()) {
            throw new NotFoundException("Espacio de evento no encontrado con el ID: " + id);
        }

        VenueModel venueModelExisting = optionalVenueModel.get();

        String nameVenue = venueModel.nameVenue() != null ? venueModel.nameVenue() : venueModelExisting.nameVenue();
        String addressVenue = venueModel.addressVenue() != null ? venueModel.addressVenue() : venueModelExisting.addressVenue();
        String cityVenue = venueModel.cityVenue() != null ? venueModel.cityVenue() : venueModelExisting.cityVenue();
        Integer capacityVenue = venueModel.capacityVenue() != null ? venueModel.capacityVenue() : venueModelExisting.capacityVenue();

        VenueModel updatedVenue = new VenueModel(
                id,
                nameVenue,
                addressVenue,
                cityVenue,
                capacityVenue
        );

        return venueRepositoryPort.save(updatedVenue);
    }
}
