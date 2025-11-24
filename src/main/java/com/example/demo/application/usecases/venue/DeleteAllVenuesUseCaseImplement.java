package com.example.demo.application.usecases.venue;

import com.example.demo.domain.ports.in.venue.DeleteAllVenuesUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;

public class DeleteAllVenuesUseCaseImplement implements DeleteAllVenuesUseCaseInterface {

    private final VenueRepositoryPort venueRepositoryPort;

    public DeleteAllVenuesUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public void deleteAll() {
        venueRepositoryPort.deleteAll();
    }
}
