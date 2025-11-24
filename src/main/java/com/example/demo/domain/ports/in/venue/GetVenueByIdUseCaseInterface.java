package com.example.demo.domain.ports.in.venue;

import com.example.demo.domain.models.VenueModel;

public interface GetVenueByIdUseCaseInterface {
    VenueModel findById(Long id);
}
