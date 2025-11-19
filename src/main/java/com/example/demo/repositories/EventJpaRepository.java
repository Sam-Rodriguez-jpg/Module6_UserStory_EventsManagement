package com.example.demo.repositories;

import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.entities.EventEntity;
import com.example.demo.enums.StatusEventEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {
    // Para crear
    boolean existsByVenueEntity_IdVenueAndDatetimeEventAndStatusEvent(Long idVenue, LocalDateTime datetimeEvent, StatusEventEnum statusEvent);

    // Para actualizar
    boolean existsByVenueEntity_IdVenueAndDatetimeEventAndStatusEventAndIdEventNot(Long idVenue, LocalDateTime datetimeEvent, StatusEventEnum statusEvent, Long idEvent);



    // FILTROS
    // Por Estado
    Page<EventEntity> findByStatusEvent(StatusEventEnum statusEvent, Pageable pageable);

    // Por ID de Espacio
    Page<EventEntity> findByVenueEntity_IdVenue(Long idVenue, Pageable pageable);

    // Por Fecha
    Page<EventEntity> findByDatetimeEvent(LocalDateTime datetimeEvent, Pageable pageable);

    // Por Ciudad
    Page<EventEntity> findByVenueEntity_CityVenue(String cityVenue, Pageable pageable);
}
