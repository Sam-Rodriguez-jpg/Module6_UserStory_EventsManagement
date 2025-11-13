package com.example.demo.controllers;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.VenueDtoResponse;
import com.example.demo.services.implement.VenueServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/venues")
public class VenueController {
    // Service
    private final VenueServiceImplement venueService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<VenueDtoResponse>> getAllVenues() {
        List<VenueDtoResponse> venueDtoResponseList = venueService.getAllVenues();
        return new ResponseEntity<>(venueDtoResponseList, HttpStatus.valueOf(200));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<VenueDtoResponse> getVenueById(@PathVariable long id) {
        VenueDtoResponse venueDtoResponse = venueService.getVenueById(id);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // POST
    @PostMapping
    public ResponseEntity<VenueDtoResponse> createVenue(@RequestBody VenueDtoRequest venueDtoRequest) {
        VenueDtoResponse venueDtoResponse = venueService.createVenue(venueDtoRequest);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(201));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<VenueDtoResponse> updateVenue(@PathVariable long id, @RequestBody VenueDtoRequest venueDtoRequest) {
        VenueDtoResponse venueDtoResponse = venueService.updateVenue(id, venueDtoRequest);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<VenueDtoResponse> partialUpdateVenue(@PathVariable long id, @RequestBody VenueDtoRequest venueDtoRequest) {
        VenueDtoResponse venueDtoResponse = venueService.partialUpdateVenue(id, venueDtoRequest);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenueById(@PathVariable long id) {
        venueService.deleteVenueById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // DELETE ALL
    @DeleteMapping
    public ResponseEntity<Void> deleteAllVenues() {
        venueService.deleteAllVenues();
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
