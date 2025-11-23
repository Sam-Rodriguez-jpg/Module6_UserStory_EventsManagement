package com.example.demo.infrastructure.adapters.in.web;

import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.services.implement.EventServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Events Operations") // Agrupación de endpoints
public class EventController {

    // CRUD
    // Service
    private final EventServiceImplement eventService;

    // GET ALL
    @Operation(summary = "Get All Events")
    @GetMapping
    public ResponseEntity<List<EventDtoResponse>> findAll() {
        List<EventDtoResponse> eventDtoResponseList = eventService.findAll();
        return new ResponseEntity<>(eventDtoResponseList, HttpStatus.valueOf(200));
    }

    // GET BY ID
    @Operation(summary = "Get Event By ID")
    @GetMapping("/{id}")
    public ResponseEntity<EventDtoResponse> findById(@PathVariable long id) {
        EventDtoResponse eventDtoResponse = eventService.findById(id);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // POST
    @Operation(summary = "Create Event")
    @PostMapping
    public ResponseEntity<EventDtoResponse> create(@RequestBody EventDtoRequest eventDtoRequest) {
        EventDtoResponse eventDtoResponse = eventService.create(eventDtoRequest);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(201));
    }

    // PUT
    @Operation(summary = "Complete Update Event")
    @PutMapping("/{id}")
    public ResponseEntity<EventDtoResponse> update(@PathVariable long id, @RequestBody EventDtoRequest eventDtoRequest) {
        EventDtoResponse eventDtoResponse = eventService.update(id, eventDtoRequest);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // PATCH
    @Operation(summary = "Partial Update Event")
    @PatchMapping("/{id}")
    public ResponseEntity<EventDtoResponse> partialUpdate(@PathVariable long id, @RequestBody EventDtoRequest eventDtoRequest) {
        EventDtoResponse eventDtoResponse = eventService.partialUpdate(id, eventDtoRequest);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // DELETE BY ID
    @Operation(summary = "Delete Event By ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        eventService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // DELETE ALL
    @Operation(summary = "Delete All Events")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        eventService.deleteAll();
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }



    // FILTROS
    // All Paginated
    @Operation(summary = "Page All Events")
    @GetMapping("/doc/allPaginated")
    public Page<EventDtoResponse> findAllPaginated(@ParameterObject Pageable pageable) {
        return eventService.findAllPaginated(pageable);
    }

    // By Status Event
    @Operation(summary = "Page By Status")
    @GetMapping("doc/byStatus")
    public Page<EventDtoResponse> findByStatusEvent(@RequestParam StatusEventEnum statusEvent, @ParameterObject Pageable pageable) {
        return eventService.findByStatusEvent(statusEvent, pageable);

    }

    // By ID Venue
    @Operation(summary = "Page By Id Venue")
    @GetMapping("/doc/byIdVenue")
    public Page<EventDtoResponse> findByVenueEntity_IdVenue(@RequestParam Long idVenue, @ParameterObject Pageable pageable) {
        return eventService.findByVenueEntity_IdVenue(idVenue, pageable);
    }

    // By Datetime
    @Operation(summary = "Page By Datetime")
    @GetMapping("/doc/byDatetime")
    public Page<EventDtoResponse> findByDatetimeEvent(@RequestParam LocalDateTime datetimeEvent, @ParameterObject Pageable pageable) {
        return eventService.findByDateTimeEvent(datetimeEvent, pageable);
    }

    // By City Venue
    @Operation(summary = "Page By City")
    @GetMapping("/doc/byCity")
    public Page<EventDtoResponse> findByVenue_City(@RequestParam String cityVenue, @ParameterObject Pageable pageable) {
        return eventService.findByVenueEntity_CityVenue(cityVenue, pageable);
    }
}
