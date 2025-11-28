package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.UpdateVenueUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import com.example.demo.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdateVenueUseCaseImplement implements UpdateVenueUseCaseInterface {

    private final VenueRepositoryPort venueRepositoryPort;

    public UpdateVenueUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public VenueModel update(Long id, VenueModel venueModel) {
        Optional<VenueModel> optionalVenueModel = venueRepositoryPort.findById(id);

        if (optionalVenueModel.isEmpty()) {
            throw new NotFoundException("Espacio de evento no encontrado con el ID: " + id);
        }

        VenueModel updatedVenue = new VenueModel(
                id,
                venueModel.nameVenue(),
                venueModel.addressVenue(),
                venueModel.cityVenue(),
                venueModel.capacityVenue()
        );

        return venueRepositoryPort.save(updatedVenue);
    }
}
