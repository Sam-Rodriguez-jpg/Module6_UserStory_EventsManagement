package com.example.demo.domain.ports.in.venue;

import com.example.demo.domain.models.VenueModel;

public interface UpdateVenueUseCaseInterface {
    VenueModel update(Long id, VenueModel venueModel);
}
