package com.example.demo.infrastructure.adapters.in.web;

import com.example.demo.domain.models.EventModel;
import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.ports.in.events.*;
import com.example.demo.infrastructure.adapters.in.web.dtos.requests.EventDtoRequest;
import com.example.demo.infrastructure.adapters.in.web.dtos.responses.EventDtoResponse;
import com.example.demo.infrastructure.adapters.in.web.mappers.EventMapperDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Events Operations") // Agrupación de endpoints
public class EventController {
    private final GetAllEventsUseCaseInterface getAllEventsUseCase;
    private final GetEventByIdUseCaseInterface getEventByIdUseCase;
    private final CreateEventUseCaseInterface createEventUseCase;
    private final UpdateEventUseCaseInterface updateEventUseCase;
    private final PartialUpdateEventUseCaseInterface partialUpdateEventUseCase;
    private final DeleteEventByIdUseCaseInterface deleteEventByIdUseCase;
    private final DeleteAllEventsUseCaseInterface deleteAllEventsUseCase;
    private final FilterEventUseCaseInterface filterEventUseCase;

    private final EventMapperDto eventMapperDto;

    public EventController(GetAllEventsUseCaseInterface getAllEventsUseCase, GetEventByIdUseCaseInterface getEventByIdUseCase, CreateEventUseCaseInterface createEventUseCase, UpdateEventUseCaseInterface updateEventUseCase, PartialUpdateEventUseCaseInterface partialUpdateEventUseCase, DeleteEventByIdUseCaseInterface deleteEventByIdUseCase, DeleteAllEventsUseCaseInterface deleteAllEventsUseCase, FilterEventUseCaseInterface filterEventUseCase, EventMapperDto eventMapperDto) {
        this.getAllEventsUseCase = getAllEventsUseCase;
        this.getEventByIdUseCase = getEventByIdUseCase;
        this.createEventUseCase = createEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.partialUpdateEventUseCase = partialUpdateEventUseCase;
        this.deleteEventByIdUseCase = deleteEventByIdUseCase;
        this.deleteAllEventsUseCase = deleteAllEventsUseCase;
        this.filterEventUseCase = filterEventUseCase;
        this.eventMapperDto = eventMapperDto;
    }

    // GET ALL
    @GetMapping
    @Operation(summary = "Get All Events")
    public ResponseEntity<List<EventDtoResponse>> getAll() {
        List<EventModel> eventModelList = getAllEventsUseCase.findAll();
        List<EventDtoResponse> eventDtoResponseList = new ArrayList<>();

        if (eventModelList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.valueOf(204));
        }

        for (EventModel eventModel : eventModelList) {
            eventDtoResponseList.add(eventMapperDto.toResponse(eventModel));
        }
        return new ResponseEntity<>(eventDtoResponseList, HttpStatus.valueOf(200));
    }

    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Event By ID")
    public ResponseEntity<EventDtoResponse> getById(@PathVariable Long id) {
        EventModel eventModel = getEventByIdUseCase.findById(id);
        EventDtoResponse eventDtoResponse = eventMapperDto.toResponse(eventModel);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // POST
    @PostMapping
    @Operation(summary = "Create Event")
    public ResponseEntity<EventDtoResponse> post(@RequestBody EventDtoRequest eventDtoRequest) {
        EventModel eventModel = eventMapperDto.toDomainModel(eventDtoRequest);
        EventModel savedEvent = createEventUseCase.create(eventModel);
        EventDtoResponse eventDtoResponse = eventMapperDto.toResponse(savedEvent);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(201));
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Update Event")
    public ResponseEntity<EventDtoResponse> put(@PathVariable Long id, @RequestBody EventDtoRequest eventDtoRequest) {
        EventModel eventModel = eventMapperDto.toDomainModel(eventDtoRequest);
        EventModel updatedEvent = updateEventUseCase.update(id, eventModel);
        EventDtoResponse eventDtoResponse = eventMapperDto.toResponse(updatedEvent);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // PATCH
    @PatchMapping("/{id}")
    @Operation(summary = "Partial Update Event")
    public ResponseEntity<EventDtoResponse> patch(@PathVariable Long id, @RequestBody EventDtoRequest eventDtoRequest) {
        EventModel eventModel = eventMapperDto.toDomainModel(eventDtoRequest);
        EventModel patchedEvent = partialUpdateEventUseCase.partialUpdate(id, eventModel);
        EventDtoResponse eventDtoResponse = eventMapperDto.toResponse(patchedEvent);
        return new ResponseEntity<>(eventDtoResponse, HttpStatus.valueOf(200));
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Event By ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        deleteEventByIdUseCase.deleteById(id);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // DELETE ALL
    @DeleteMapping
    @Operation(summary = "Delete All Events")
    public ResponseEntity<Void> deleteAll() {
        deleteAllEventsUseCase.deleteAll();
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }

    // FILTER
    @GetMapping("/filter")
    @Operation(summary = "Filters")
    public ResponseEntity<Page<EventDtoResponse>> filter(
            @RequestParam(required = false) StatusEventEnum statusEvent,
            @RequestParam(required = false) Long idVenue,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime datetimeEvent,
            @RequestParam(required = false) String cityEvent,
            @ParameterObject Pageable pageable
    ) {
        Page<EventModel> eventModelPage = filterEventUseCase.filter(statusEvent, idVenue, datetimeEvent, cityEvent, pageable);
        Page<EventDtoResponse> eventDtoResponsePage = eventModelPage.map(eventMapperDto::toResponse);

        return new ResponseEntity<>(eventDtoResponsePage, HttpStatus.valueOf(200));
    }
}
