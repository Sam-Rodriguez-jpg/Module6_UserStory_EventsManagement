package com.example.demo.services.implement;

import com.example.demo.dtos.requests.EventDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.entities.EventEntity;
import com.example.demo.entities.VenueEntity;
import com.example.demo.enums.StatusEventEnum;
import com.example.demo.exceptions.custom.BadRequestException;
import com.example.demo.exceptions.custom.ConflictException;
import com.example.demo.exceptions.custom.NoContentException;
import com.example.demo.exceptions.custom.NotFoundException;
import com.example.demo.mappers.EventMapper;
import com.example.demo.repositories.EventJpaRepository;
import com.example.demo.repositories.VenueJpaRepository;
import com.example.demo.services.interfaces.EventServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImplement implements EventServiceInterface {

    // CRUD
    // Database
    private final EventJpaRepository eventJpaRepository;
    private final VenueJpaRepository venueJpaRepository;


    // Mapper
    private final EventMapper eventMapper;

    // GET ALL
    @Override
    public List<EventDtoResponse> findAll() {

        // Obtener todos los events del repository
        List<EventEntity> eventEntitiesList = eventJpaRepository.findAll();

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
    public EventDtoResponse findById(long id) {
        // Buscar evento dentro de la lista
        Optional<EventEntity> optionalEventEntity = eventJpaRepository.findById(id);

        // Si no existe, lanza lanza la excepción personalizada
        if (optionalEventEntity.isEmpty()) {
            throw new NotFoundException("Evento con ID: " + id + " no encontrado");
        }

        // Si existe, convertir a Dto Response y devolver
        return eventMapper.toResponse(optionalEventEntity.get());
    }


    // POST
    @Override
    public EventDtoResponse create(EventDtoRequest eventDtoRequest) {

        // Validar campos vacíos
        if (eventDtoRequest.nameEvent() == null ||
            eventDtoRequest.datetimeEvent() == null ||
            eventDtoRequest.durationEvent() == null || eventDtoRequest.durationEvent() <= 0 ||
            eventDtoRequest.priceEvent() == null || eventDtoRequest.priceEvent() < 0 ||
            eventDtoRequest.statusEvent() == null ||
            eventDtoRequest.idVenue() == null) {
            throw new BadRequestException("No puedes dejar campos vacíos");
        }

        // Comprobar que el venue exista
        Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(eventDtoRequest.idVenue());
        if (optionalVenueEntity.isEmpty()) {
            throw new NotFoundException("Venue con ID " + eventDtoRequest.idVenue() + " no existe");
        }

        // Validar espacio ocupado, fecha y hora y si el evento esta activo todavía
        boolean venueOcupied = eventJpaRepository.existsByVenueEntity_IdVenueAndDatetimeEventAndStatusEvent(eventDtoRequest.idVenue(), eventDtoRequest.datetimeEvent(), StatusEventEnum.Active);
        if (venueOcupied) {
            throw new ConflictException("El venue ya esta reservado para esta fecha");
        }

        // Se convierte de Dto Request a Entity
        EventEntity eventEntity = eventMapper.toEntity(eventDtoRequest);
        eventEntity.setVenueEntity(optionalVenueEntity.get());

        // Se guarda en la lista
        EventEntity savedEvent = eventJpaRepository.save(eventEntity);

        // Se convierte de Entity a Dto Response y lo devuelve
        return eventMapper.toResponse(savedEvent);
    }

    // PUT
    @Override
    public EventDtoResponse update(long id, EventDtoRequest eventDtoRequest) {

        // Buscar si existe
        Optional<EventEntity> optionalEventEntity = eventJpaRepository.findById(id);

        // Si no existe
        if (optionalEventEntity.isEmpty()) throw new NotFoundException("Evento con ID: " + id + " no encontrado");

        // La información del objeto optional se pasa a la entidad
        EventEntity eventEntity = optionalEventEntity.get();

        // Validar los campos
        if (eventDtoRequest.nameEvent() == null ||
            eventDtoRequest.datetimeEvent() == null ||
            eventDtoRequest.durationEvent() == null || eventDtoRequest.durationEvent() <= 0 ||
            eventDtoRequest.priceEvent() == null || eventDtoRequest.priceEvent() < 0 ||
            eventDtoRequest.statusEvent() == null ||
            eventDtoRequest.idVenue() == null
        ) throw new BadRequestException("No puedes dejar campos vacíos");

        // Comprobar que el venue exista
        Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(eventDtoRequest.idVenue());
        if (optionalVenueEntity.isEmpty()) {
            throw new NotFoundException("Venue con ID " + eventDtoRequest.idVenue() + " no existe");
        }

        // Validación de reserva
        boolean venueOcupied = eventJpaRepository.existsByVenueEntity_IdVenueAndDatetimeEventAndStatusEventAndIdEventNot(
                eventDtoRequest.idVenue(),
                eventDtoRequest.datetimeEvent(),
                StatusEventEnum.Active,
                id
        );

        // Si el espacio esta ocupado
        if (venueOcupied) throw new ConflictException("El venue ya esta reservado para esta fecha");

        // Actualiza los campos los valores
        eventEntity.setNameEvent(eventDtoRequest.nameEvent());
        eventEntity.setDescriptionEvent(eventDtoRequest.descriptionEvent());
        eventEntity.setDatetimeEvent(eventDtoRequest.datetimeEvent());
        eventEntity.setDurationEvent(eventDtoRequest.durationEvent());
        eventEntity.setPriceEvent(eventDtoRequest.priceEvent());
        eventEntity.setStatusEvent(eventDtoRequest.statusEvent());

        // Venue
        eventEntity.setVenueEntity(optionalVenueEntity.get());

        EventEntity savedEventEntity = eventJpaRepository.save(eventEntity);

        // Devolver como Dto Response
        return eventMapper.toResponse(savedEventEntity);
    }

    // PATCH
    @Override
    public EventDtoResponse partialUpdate(long id, EventDtoRequest eventDtoRequest) {

        // Buscar si existe
        Optional<EventEntity> optionalEventEntity = eventJpaRepository.findById(id);
        if (optionalEventEntity.isEmpty()) throw new NotFoundException("Evento con ID: " + id + " no encontrado");

        // La información del objeto optional se pasa a la entidad
        EventEntity eventEntity = optionalEventEntity.get();

        // Reemplazar los valores
        if (eventDtoRequest.nameEvent() != null) eventEntity.setNameEvent(eventDtoRequest.nameEvent());
        if (eventDtoRequest.descriptionEvent() != null) eventEntity.setDescriptionEvent(eventDtoRequest.descriptionEvent());
        if (eventDtoRequest.datetimeEvent() != null) eventEntity.setDatetimeEvent(eventDtoRequest.datetimeEvent());
        if (eventDtoRequest.durationEvent() != null && eventDtoRequest.durationEvent() > 0) eventEntity.setDurationEvent(eventDtoRequest.durationEvent());
        if (eventDtoRequest.priceEvent() != null && eventDtoRequest.priceEvent() >= 0) eventEntity.setPriceEvent(eventDtoRequest.priceEvent());
        if (eventDtoRequest.statusEvent() != null) eventEntity.setStatusEvent(eventDtoRequest.statusEvent());

        // Venue
        VenueEntity venueEntity = eventEntity.getVenueEntity();
        LocalDateTime localDateTime = eventEntity.getDatetimeEvent();

        // Verificar que el espacio no esta ocupado
        if (eventDtoRequest.idVenue() != null && eventDtoRequest.idVenue() > 0) {
            Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(eventDtoRequest.idVenue());
            if (optionalVenueEntity.isEmpty()) {
                throw new NotFoundException("Venue con ID " + eventDtoRequest.idVenue() + " no existe");
            }

            venueEntity = optionalVenueEntity.get();
            eventEntity.setVenueEntity(optionalVenueEntity.get());
        }

        // Si se envió la fecha, se actualiza el valor final
        if (eventDtoRequest.datetimeEvent() != null) {
            localDateTime = eventDtoRequest.datetimeEvent();
        }


        if (venueEntity != null && localDateTime != null) {

            boolean venueOcupied = eventJpaRepository
                    .existsByVenueEntity_IdVenueAndDatetimeEventAndStatusEventAndIdEventNot(
                            venueEntity.getIdVenue(),
                            localDateTime,
                            StatusEventEnum.Active,
                            id
                    );

            if (venueOcupied) {
                throw new ConflictException("El venue ya esta reservado para esta fecha");
            }
        }

        // Guardar cambios
        EventEntity savedEventEntity = eventJpaRepository.save(eventEntity);

        // Devolver como Dto Response
        return eventMapper.toResponse(savedEventEntity);
    }

    // DELETE BY ID
    @Override
    public void deleteById(long id) {
        // Buscar si existe
        Optional<EventEntity> optionalEventEntity = eventJpaRepository.findById(id);

        // Si no existe
        if (optionalEventEntity.isEmpty()) throw new NotFoundException("Evento con ID: " + id + " no encontrado");

        // Si lo encuentra, lo elimina
        eventJpaRepository.deleteById(id);
    }

    // DELETE ALL
    @Override
    public void deleteAll() {
        // Obtener todos los events del repository
        List<EventEntity> eventEntitiesList = eventJpaRepository.findAll();

        // Validar si esta vacía la lista
        if (eventEntitiesList.isEmpty()) {
            throw new NoContentException("No hay registros en la base de datos");
        }

        eventJpaRepository.deleteAll();
    }



    // FILTROS
    @Override
    public Page<EventDtoResponse> findAllPaginated(Pageable pageable) {
        Page<EventEntity> eventEntityPage = eventJpaRepository.findAll(pageable);

        List<EventDtoResponse> eventDtoResponseList = new ArrayList<>();

        for (EventEntity eventEntity : eventEntityPage.getContent()) {
            EventDtoResponse eventDtoResponse = eventMapper.toResponse(eventEntity);
            eventDtoResponseList.add(eventDtoResponse);
        }

        return new PageImpl<>(
                eventDtoResponseList,
                pageable,
                eventEntityPage.getTotalElements()
        );
    }

    @Override
    public Page<EventDtoResponse> findByStatusEvent(StatusEventEnum statusEvent, Pageable pageable) {
        Page<EventEntity> eventEntityPage = eventJpaRepository.findByStatusEvent(statusEvent, pageable);

        List<EventDtoResponse> eventDtoResponseList = new ArrayList<>();

        for (EventEntity eventEntity : eventEntityPage.getContent()) {
            EventDtoResponse eventDtoResponse = eventMapper.toResponse(eventEntity);
            eventDtoResponseList.add(eventDtoResponse);
        }

        return new PageImpl<>(
                eventDtoResponseList,
                pageable,
                eventEntityPage.getTotalElements()
        );
    }

    @Override
    public Page<EventDtoResponse> findByVenueEntity_IdVenue(Long idVenue, Pageable pageable) {
        Page<EventEntity> eventEntityPage = eventJpaRepository.findByVenueEntity_IdVenue(idVenue, pageable);

        List<EventDtoResponse> eventDtoResponseList = new ArrayList<>();

        for (EventEntity eventEntity : eventEntityPage.getContent()) {
            EventDtoResponse eventDtoResponse = eventMapper.toResponse(eventEntity);
            eventDtoResponseList.add(eventDtoResponse);
        }

        return new PageImpl<>(
                eventDtoResponseList,
                pageable,
                eventEntityPage.getTotalElements()
        );
    }

    @Override
    public Page<EventDtoResponse> findByDateTimeEvent(LocalDateTime datetimeEvent, Pageable pageable) {
        Page<EventEntity> eventEntityPage = eventJpaRepository.findByDatetimeEvent(datetimeEvent, pageable);

        List<EventDtoResponse> eventDtoResponseList = new ArrayList<>();

        for (EventEntity eventEntity : eventEntityPage.getContent()) {
            EventDtoResponse eventDtoResponse = eventMapper.toResponse(eventEntity);
            eventDtoResponseList.add(eventDtoResponse);
        }

        return new PageImpl<>(
                eventDtoResponseList,
                pageable,
                eventEntityPage.getTotalElements()
        );
    }

    @Override
    public Page<EventDtoResponse> findByVenueEntity_CityVenue(String cityVenue, Pageable pageable) {
        Page<EventEntity> eventEntityPage = eventJpaRepository.findByVenueEntity_CityVenue(cityVenue, pageable);

        List<EventDtoResponse> eventDtoResponseList = new ArrayList<>();

        for (EventEntity eventEntity : eventEntityPage.getContent()) {
            EventDtoResponse eventDtoResponse = eventMapper.toResponse(eventEntity);
            eventDtoResponseList.add(eventDtoResponse);
        }

        return new PageImpl<>(
                eventDtoResponseList,
                pageable,
                eventEntityPage.getTotalElements()
        );
    }
}
