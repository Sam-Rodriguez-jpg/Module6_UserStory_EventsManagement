package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.GetAllVenuesUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;

import java.util.List;

public class GetAllVenuesUseCaseImplement implements GetAllVenuesUseCaseInterface {

    private final VenueRepositoryPort venueRepositoryPort;

    public GetAllVenuesUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public List<VenueModel> findAll() {
        return venueRepositoryPort.findAll();
    }
}
