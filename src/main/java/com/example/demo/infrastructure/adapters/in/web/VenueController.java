package com.example.demo.infrastructure.adapters.in.web;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.VenueDtoResponse;
import com.example.demo.services.implement.VenueServiceImplement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/venues")
@Tag(name = "Venues", description = "Venues Operations")
public class VenueController {
    // Service
    private final VenueServiceImplement venueService;

    // GET ALL
    @Operation(summary = "Get All Venues")
    @GetMapping
    public ResponseEntity<List<VenueDtoResponse>> findAll() {
        List<VenueDtoResponse> venueDtoResponseList = venueService.findAll();
        return new ResponseEntity<>(venueDtoResponseList, HttpStatus.valueOf(200));
    }

    // GET BY ID
    @Operation(summary = "Get Venue By ID")
    @GetMapping("/{id}")
    public ResponseEntity<VenueDtoResponse> findById(@PathVariable long id) {
        VenueDtoResponse venueDtoResponse = venueService.findById(id);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // POST
    @Operation(summary = "Create Venue")
    @PostMapping
    public ResponseEntity<VenueDtoResponse> create(@RequestBody VenueDtoRequest venueDtoRequest) {
        VenueDtoResponse venueDtoResponse = venueService.create(venueDtoRequest);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(201));
    }

    // PUT
    @Operation(summary = "Complete Update Venue")
    @PutMapping("/{id}")
    public ResponseEntity<VenueDtoResponse> update(@PathVariable long id, @RequestBody VenueDtoRequest venueDtoRequest) {
        VenueDtoResponse venueDtoResponse = venueService.update(id, venueDtoRequest);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // PATCH
    @Operation(summary = "Partial Update Venue")
    @PatchMapping("/{id}")
    public ResponseEntity<VenueDtoResponse> partialUpdate(@PathVariable long id, @RequestBody VenueDtoRequest venueDtoRequest) {
        VenueDtoResponse venueDtoResponse = venueService.partialUpdate(id, venueDtoRequest);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // DELETE BY ID
    @Operation(summary = "Delete Venue By ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        venueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // DELETE ALL
    @Operation(summary = "Delete All Venues")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        venueService.deleteAll();
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
