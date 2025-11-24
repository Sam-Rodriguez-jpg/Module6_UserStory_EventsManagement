package com.example.demo.domain.ports.in.venue;

import com.example.demo.domain.models.VenueModel;

public interface PartialUpdateVenueUseCaseInterface {
    VenueModel partialUpdate(Long id, VenueModel venueModel);
}
