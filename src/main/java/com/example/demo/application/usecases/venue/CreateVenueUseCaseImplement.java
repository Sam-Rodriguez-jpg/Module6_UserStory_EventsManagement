package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.CreateVenueUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateVenueUseCaseImplement implements CreateVenueUseCaseInterface {

    private final VenueRepositoryPort venueRepositoryPort;

    public CreateVenueUseCaseImplement(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public VenueModel create(VenueModel venueModel) {
        return venueRepositoryPort.save(venueModel);
    }
}
