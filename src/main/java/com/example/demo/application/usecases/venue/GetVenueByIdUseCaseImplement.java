package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.GetVenueByIdUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import com.example.demo.infrastructure.adapters.in.web.exceptions.custom.NotFoundException;

import java.util.Optional;

public class GetVenueByIdUseCaseImplement implements GetVenueByIdUseCaseInterface {

    private final VenueRepositoryPort venueRepositoryPort;

    public GetVenueByIdUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public VenueModel findById(Long id) {

        Optional<VenueModel> optionalVenueModel = venueRepositoryPort.findById(id);

        if (optionalVenueModel.isEmpty()) {
            throw new NotFoundException("Espacio de evento no encontrado con el ID: " + id);
        }

        return optionalVenueModel.get();
    }
}
