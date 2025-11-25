package com.example.demo.infrastructure.adapters.out.jpa.repositories;


import com.example.demo.infrastructure.adapters.out.jpa.entites.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueJpaRepository extends JpaRepository<VenueEntity, Long> {}
