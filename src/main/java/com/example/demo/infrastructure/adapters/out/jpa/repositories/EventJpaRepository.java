package com.example.demo.infrastructure.adapters.out.jpa.repositories;

import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {

    // Consulta avanzada con JPQL (Java Persistence Query Language)
    @Query("SELECT events FROM EventEntity events JOIN FETCH events.venueEntity venues WHERE venues.idVenue = :idVenue")
    List<EventEntity> findByVenueIdFetch(@Param("idVenue") Long idVenue);

    // Elimination del N+1 con EntityGraph
    @EntityGraph(attributePaths = {"venueEntity"})
    @Query("SELECT events FROM EventEntity events")
    List<EventEntity> findAllWithVenue();
}
