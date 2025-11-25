package com.example.demo.infrastructure.adapters.out.jpa.adapters;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import com.example.demo.infrastructure.adapters.out.jpa.entites.VenueEntity;
import com.example.demo.infrastructure.adapters.out.jpa.mappers.VenueMapperModel;
import com.example.demo.infrastructure.adapters.out.jpa.repositories.VenueJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository venueJpaRepository;
    private final VenueMapperModel venueMapper;

    public VenueJpaAdapter(VenueJpaRepository venueJpaRepository, VenueMapperModel venueMapper) {
        this.venueJpaRepository = venueJpaRepository;
        this.venueMapper = venueMapper;
    }

    // SAVE
    @Override
    public VenueModel save(VenueModel venueModel) {
        VenueEntity venueEntity = venueMapper.toEntity(venueModel);
        VenueEntity savedVenue = venueJpaRepository.save(venueEntity);
        return venueMapper.toModel(savedVenue);
    }

    // FIND ALL
    @Override
    public List<VenueModel> findAll() {
        List<VenueEntity> venueEntityList = venueJpaRepository.findAll();
        List<VenueModel> venueModelList = new ArrayList<>();

        for (VenueEntity venueEntity : venueEntityList) {
            venueModelList.add(venueMapper.toModel(venueEntity));
        }

        return venueModelList;
    }

    // FIND BY ID
    @Override
    public Optional<VenueModel> findById(Long id) {
        Optional<VenueEntity> venueEntity = venueJpaRepository.findById(id);

        if (venueEntity.isEmpty()) {
            return Optional.empty();
        }

        VenueModel venueModel = venueMapper.toModel(venueEntity.get());
        return Optional.of(venueModel);
    }

    @Override
    public void deleteById(Long id) {
        venueJpaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        venueJpaRepository.deleteAll();
    }
}
