package com.example.demo.infrastructure.adapters.in.web;

import com.example.demo.domain.models.VenueModel;
import com.example.demo.domain.ports.in.venue.*;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.VenueDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.VenueDtoResponse;
import com.example.demo.infrastructure.adapters.in.web.mappers.VenueMapperDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/venues")
@Tag(name = "Venues", description = "Venues Operations")
public class VenueController {
    private final GetAllVenuesUseCaseInterface getAllVenuesUseCase;
    private final GetVenueByIdUseCaseInterface getVenueByIdUseCase;
    private final CreateVenueUseCaseInterface createVenueUseCase;
    private final UpdateVenueUseCaseInterface updateVenueUseCase;
    private final PartialUpdateVenueUseCaseInterface partialUpdateVenueUseCase;
    private final DeleteVenueByIdUseCaseInterface deleteVenueByIdUseCase;
    private final DeleteAllVenuesUseCaseInterface deleteAllVenuesUseCaseInterface;

    private final VenueMapperDto venueMapperDto;

    public VenueController(GetAllVenuesUseCaseInterface getAllVenuesUseCase, GetVenueByIdUseCaseInterface getVenueByIdUseCase, CreateVenueUseCaseInterface createVenueUseCase, UpdateVenueUseCaseInterface updateVenueUseCase, PartialUpdateVenueUseCaseInterface partialUpdateVenueUseCase, DeleteVenueByIdUseCaseInterface deleteVenueByIdUseCase, DeleteAllVenuesUseCaseInterface deleteAllVenuesUseCaseInterface, VenueMapperDto venueMapperDto) {
        this.getAllVenuesUseCase = getAllVenuesUseCase;
        this.getVenueByIdUseCase = getVenueByIdUseCase;
        this.createVenueUseCase = createVenueUseCase;
        this.updateVenueUseCase = updateVenueUseCase;
        this.partialUpdateVenueUseCase = partialUpdateVenueUseCase;
        this.deleteVenueByIdUseCase = deleteVenueByIdUseCase;
        this.deleteAllVenuesUseCaseInterface = deleteAllVenuesUseCaseInterface;
        this.venueMapperDto = venueMapperDto;
    }

    // GET ALL
    @GetMapping
    @Operation(summary = "Get All Venues")
    public ResponseEntity<List<VenueDtoResponse>> getAll() {
        List<VenueModel> venueModelList = getAllVenuesUseCase.findAll();
        List<VenueDtoResponse> venueDtoResponseList = new ArrayList<>();

        if (venueModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.valueOf(204));
        }

        for (VenueModel venueModel : venueModelList) {
            venueDtoResponseList.add(venueMapperDto.toResponse(venueModel));
        }
        return new ResponseEntity<>(venueDtoResponseList, HttpStatus.valueOf(200));
    }

    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Venues By ID")
    public ResponseEntity<VenueDtoResponse> getById(@PathVariable Long id) {
        VenueModel venueModel = getVenueByIdUseCase.findById(id);
        VenueDtoResponse venueDtoResponse = venueMapperDto.toResponse(venueModel);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // POST
    @PostMapping
    @Operation(summary = "Create Venue")
    public ResponseEntity<VenueDtoResponse> post(@RequestBody VenueDtoRequest venueDtoRequest) {
        VenueModel venueModel = venueMapperDto.toDomainModel(venueDtoRequest);
        VenueModel savedVenue = createVenueUseCase.create(venueModel);
        VenueDtoResponse venueDtoResponse = venueMapperDto.toResponse(savedVenue);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(201));
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Update Venue")
    public ResponseEntity<VenueDtoResponse> put(@PathVariable Long id, @RequestBody VenueDtoRequest venueDtoRequest) {
        VenueModel venueModel = venueMapperDto.toDomainModel(venueDtoRequest);
        VenueModel updatedVenue = updateVenueUseCase.update(id, venueModel);
        VenueDtoResponse venueDtoResponse = venueMapperDto.toResponse(updatedVenue);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // PATCH
    @PatchMapping("/{id}")
    @Operation(summary = "Partial Update Venue")
    public ResponseEntity<VenueDtoResponse> patch(@PathVariable Long id, @RequestBody VenueDtoRequest venueDtoRequest) {
        VenueModel venueModel = venueMapperDto.toDomainModel(venueDtoRequest);
        VenueModel patchedVenue = partialUpdateVenueUseCase.partialUpdate(id, venueModel);
        VenueDtoResponse venueDtoResponse = venueMapperDto.toResponse(patchedVenue);
        return new ResponseEntity<>(venueDtoResponse, HttpStatus.valueOf(200));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Venue By ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        deleteVenueByIdUseCase.deleteById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // DELETE ALL
    @DeleteMapping
    @Operation(summary = "Delete All Venues")
    public ResponseEntity<Void> deleteAll() {
        deleteAllVenuesUseCaseInterface.deleteAll();
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
