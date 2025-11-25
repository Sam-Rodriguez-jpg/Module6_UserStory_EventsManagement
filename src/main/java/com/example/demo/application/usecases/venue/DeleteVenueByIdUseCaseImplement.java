package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.DeleteVenueByIdUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import com.example.demo.exceptions.custom.NotFoundException;

import java.util.Optional;

public class DeleteVenueByIdUseCaseImplement implements DeleteVenueByIdUseCaseInterface {
    private final VenueRepositoryPort venueRepositoryPort;

    public DeleteVenueByIdUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    public void deleteById(Long id) {
        Optional<VenueModel> optionalVenueModel = venueRepositoryPort.findById(id);

        if (optionalVenueModel.isEmpty()) {
            throw new NotFoundException("Espacio de evento no encontrado con el ID: " + id);
        }

        venueRepositoryPort.deleteById(id);
    }
}
