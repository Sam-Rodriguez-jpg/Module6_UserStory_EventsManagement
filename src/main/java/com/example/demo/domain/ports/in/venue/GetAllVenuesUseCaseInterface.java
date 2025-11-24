package com.example.demo.domain.ports.in.venue;

import com.example.demo.domain.models.VenueModel;

import java.util.List;

public interface GetAllVenuesUseCaseInterface {
    List<VenueModel> findAll();
}
