package com.example.demo.controllers;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.services.implement.EventServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    // Service
    private final EventServiceImplement eventService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<EventDtoResponse>> getAllEvents() {
        List<EventDtoResponse> eventDtoResponseList = eventService.getAllEvents();
        return new ResponseEntity<>(eventDtoResponseList, HttpStatus.valueOf(200));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<EventDtoResponse> getVenueById(@PathVariable long id) {
        EventDtoResponse eventDtoResponse = eventService.getEventById(id);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // POST
    @PostMapping
    public ResponseEntity<EventDtoResponse> createVenue(@RequestBody EventDtoRequest eventDtoRequest) {
        EventDtoResponse eventDtoResponse = eventService.createEvent(eventDtoRequest);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(201));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<EventDtoResponse> updateVenue(@PathVariable long id, @RequestBody EventDtoRequest eventDtoRequest) {
        EventDtoResponse eventDtoResponse = eventService.updateEvent(id, eventDtoRequest);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<EventDtoResponse> partialUpdateVenue(@PathVariable long id, @RequestBody EventDtoRequest eventDtoRequest) {
        EventDtoResponse eventDtoResponse = eventService.partialUpdateEvent(id, eventDtoRequest);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenueById(@PathVariable long id) {
        eventService.deleteEventById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // DELETE ALL
    @DeleteMapping
    public ResponseEntity<Void> deleteAllEvents() {
        eventService.deleteAllEvents();
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
