package com.example.demo.services.implement;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.entities.EventEntity;
import com.example.demo.exceptions.custom.BadRequestException;
import com.example.demo.exceptions.custom.NoContentException;
import com.example.demo.exceptions.custom.NotFoundException;
import com.example.demo.mappers.EventMapper;
import com.example.demo.repositories.implement.EventRepositoryImplement;
import com.example.demo.services.interfaces.EventServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImplement implements EventServiceInterface {
    // Database
    private final EventRepositoryImplement eventRepository = new EventRepositoryImplement();

    // Mapper para convertir entre Dto y Entity
    private final EventMapper eventMapper = new EventMapper();

    // GET ALL
    @Override
    public List<EventDtoResponse> getAllEvents() {

        // Obtener todos los venues del repository
        List<EventEntity> eventEntitiesList = eventRepository.getAllEvents();

        // Validar si esta vacía la lista
        if (eventEntitiesList.isEmpty()) {
            throw new NoContentException("No hay registros en la base de datos");
        }

        // Convertir Entity a Dto Response
        List<EventDtoResponse> eventDtoResponsesList = new ArrayList<>();
        // A cada entidad dentro de la base de datos, la voy a convertir en Dto Response
        for (EventEntity event : eventEntitiesList) {
            EventDtoResponse eventDtoResponse = eventMapper.toResponse(event);
            eventDtoResponsesList.add(eventDtoResponse);
        }

        // Devolver la lista con Dto Responses
        return eventDtoResponsesList;
    }

    // GET BY ID
    @Override
    public EventDtoResponse getEventById(long id) {
        // Buscar venue dentro de la lista
        EventEntity eventEntity = eventRepository.getEventById(id);

        // Si no existe, lanza lanza la excepción personalizada
        if (eventEntity == null) {
            throw new NotFoundException("Evento con ID: " + id + " no encontrado");
        }

        // Si existe, convertir a Dto Response y devolver
        return eventMapper.toResponse(eventEntity);
    }


    // POST
    @Override
    public EventDtoResponse createEvent(EventDtoRequest eventDtoRequest) {

        if (eventDtoRequest.nameEvent() == null || eventDtoRequest.datetimeEvent() == null || eventDtoRequest.durationEvent() == null || eventDtoRequest.durationEvent() <= 0 || eventDtoRequest.priceEvent() == null || eventDtoRequest.priceEvent() < 0 || eventDtoRequest.statusEvent() == null || eventDtoRequest.idVenue() == null) {
            throw new BadRequestException("No puedes dejar campos vacíos");
        }

        // Se convierte de Dto Request a Entity
        EventEntity eventEntity = eventMapper.toEntity(eventDtoRequest);

        // Se guarda en la lista
        EventEntity savedEvent = eventRepository.createEvent(eventEntity);

        // Se convierte de Entity a Dto Response y lo devuelve
        return eventMapper.toResponse(savedEvent);
    }

    // PUT
    @Override
    public EventDtoResponse updateEvent(long id, EventDtoRequest eventDtoRequest) {

        // Buscar si existe
        EventEntity eventEntity = eventRepository.getEventById(id);
        if (eventEntity == null) throw new NotFoundException("Evento con ID: " + id + " no encontrado");

        // Reemplazar los valores
        eventEntity.setNameEvent(eventDtoRequest.nameEvent());
        eventEntity.setDescriptionEvent(eventDtoRequest.descriptionEvent());
        eventEntity.setDatetimeEvent(eventDtoRequest.datetimeEvent());
        eventEntity.setDurationEvent(eventDtoRequest.durationEvent());
        eventEntity.setPriceEvent(eventDtoRequest.priceEvent());
        eventEntity.setStatusEvent(eventDtoRequest.statusEvent());
        eventEntity.setIdEvent(eventDtoRequest.idVenue());

        // Devolver como Dto Response
        return eventMapper.toResponse(eventEntity);
    }

    // PATCH
    @Override
    public EventDtoResponse partialUpdateEvent(long id, EventDtoRequest eventDtoRequest) {

        // Buscar si existe
        EventEntity eventEntity = eventRepository.getEventById(id);
        if (eventEntity == null) throw new NotFoundException("Evento con ID: " + id + " no encontrado");

        // Reemplazar los valores
        if (eventDtoRequest.nameEvent() != null) eventEntity.setNameEvent(eventDtoRequest.nameEvent());
        if (eventDtoRequest.descriptionEvent() != null) eventEntity.setNameEvent(eventDtoRequest.descriptionEvent());
        if (eventDtoRequest.datetimeEvent() != null) eventEntity.setDatetimeEvent(eventDtoRequest.datetimeEvent());
        if (eventDtoRequest.durationEvent() != null && eventDtoRequest.durationEvent() > 0) eventEntity.setDurationEvent(eventDtoRequest.durationEvent());
        if (eventDtoRequest.priceEvent() != null && eventDtoRequest.priceEvent() >= 0) eventEntity.setPriceEvent(eventDtoRequest.priceEvent());
        if (eventDtoRequest.statusEvent() != null) eventEntity.setStatusEvent(eventDtoRequest.statusEvent());
        if (eventDtoRequest.idVenue() != null && eventDtoRequest.idVenue() > 0) eventEntity.setIdVenue(eventDtoRequest.idVenue());

        // Devolver como Dto Response
        return eventMapper.toResponse(eventEntity);
    }

    // DELETE BY
    @Override
    public void deleteEventById(long id) {
        // Buscar si existe
        EventEntity eventEntity = eventRepository.getEventById(id);
        if (eventEntity == null) throw new NotFoundException("Evento con ID: " + id + " no encontrado");

        // Si lo encuentra, lo elimina
        eventRepository.deleteEvent(id);
    }

    // DELETE ALL
    @Override
    public void deleteAllEvents() {
        eventRepository.deleteAllEvents();
    }
}
