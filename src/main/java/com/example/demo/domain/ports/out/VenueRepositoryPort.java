package com.example.demo.domain.ports.out;

import com.example.demo.domain.models.VenueModel;

import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
     VenueModel save(VenueModel venueModel);
     Optional<VenueModel> findById(Long id);
     List<VenueModel> findAll();
     void deleteById(Long id);
     void deleteAll();
}
