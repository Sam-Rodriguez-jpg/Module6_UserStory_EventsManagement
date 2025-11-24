package com.example.demo.services.interfaces;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.domain.models.enums.StatusEventEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface EventServiceInterface {
    // CRUD
    List<EventDtoResponse> findAll();
    EventDtoResponse findById(long id);
    EventDtoResponse create(EventDtoRequest eventDtoRequest);
    EventDtoResponse update(long id, EventDtoRequest eventDtoRequest);
    EventDtoResponse partialUpdate(long id, EventDtoRequest eventDtoRequest);
    void deleteById(long id);
    void deleteAll();

    // FILTROS
    // Todos
    Page<EventDtoResponse> findAllPaginated(Pageable pageable);

    // Por Estado
    Page<EventDtoResponse> findByStatusEvent(StatusEventEnum statusEvent, Pageable pageable);

    // Por ID de Espacio
    Page<EventDtoResponse> findByVenueEntity_IdVenue(Long idVenue, Pageable pageable);

    // Por Fecha
    Page<EventDtoResponse> findByDateTimeEvent(LocalDateTime datetimeEvent, Pageable pageable);

    // Por Ciudad
    Page<EventDtoResponse> findByVenueEntity_CityVenue(String cityVenue, Pageable pageable);
}
