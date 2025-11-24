package com.example.demo.domain.ports.in.venue;

import com.example.demo.domain.models.VenueModel;

public interface CreateVenueUseCaseInterface {
    VenueModel create(VenueModel venueModel);
}
