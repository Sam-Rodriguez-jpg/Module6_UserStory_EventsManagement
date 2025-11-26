package com.example.demo.application.usecases.venue;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.GetAllVenuesUseCaseInterface;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
