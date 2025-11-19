package com.example.demo.services.implement;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.dtos.responses.VenueDtoResponse;
import com.example.demo.entities.VenueEntity;
import com.example.demo.exceptions.custom.BadRequestException;
import com.example.demo.exceptions.custom.NoContentException;
import com.example.demo.exceptions.custom.NotFoundException;
import com.example.demo.mappers.VenueMapper;
import com.example.demo.repositories.VenueJpaRepository;
import com.example.demo.services.interfaces.VenueServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VenueServiceImplement implements VenueServiceInterface {

    // Database
    private final VenueJpaRepository venueJpaRepository;

    // Mapper
    private final VenueMapper venueMapper;

    // GET ALL
    @Override
    public List<VenueDtoResponse> findAll() {

        // Obtener todos los venues del repository
        List<VenueEntity> venueEntitiesList = venueJpaRepository.findAll();

        // Validar si esta vacía la lista
        if (venueEntitiesList.isEmpty()) {
            throw new NoContentException("No hay registros en la base de datos");
        }

        // Convertir Entity a Dto Response
        List<VenueDtoResponse> venueDtoResponsesList = new ArrayList<>();
        // A cada entidad dentro de la base de datos, la voy a convertir en Dto Response
        for (VenueEntity venue : venueEntitiesList) {
            VenueDtoResponse venueDtoResponse = venueMapper.toResponse(venue);
            venueDtoResponsesList.add(venueDtoResponse);
        }

        // Devolver la lista con Dto Responses
        return venueDtoResponsesList;
    }

    // GET BY ID
    @Override
    public VenueDtoResponse findById(long id) {
        // Buscar venue dentro de la lista
        Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(id);

        // Si no existe, lanza lanza la excepción personalizada
        if (optionalVenueEntity.isEmpty()) {
            throw new NotFoundException("Espacio con ID: " + id + " no encontrado");
        }

        // Si existe, convertir a Dto Response y devolver
        return venueMapper.toResponse(optionalVenueEntity.get());
    }


    // POST
    @Override
    public VenueDtoResponse create(VenueDtoRequest venueDtoRequest) {

        // Validar campos vacíos
        if (venueDtoRequest.nameVenue() == null ||
            venueDtoRequest.cityVenue() == null ||
            venueDtoRequest.addressVenue() == null) {
            throw new BadRequestException("No puedes dejar campos vacíos");
        }
        // Se convierte de Dto Request a Entity
        VenueEntity venueEntity = venueMapper.toEntity(venueDtoRequest);

        // Se guarda en la lista
        VenueEntity savedVenue = venueJpaRepository.save(venueEntity);

        // Se convierte de Entity a Dto Response y lo devuelve
        return venueMapper.toResponse(savedVenue);
    }

    // PUT
    @Override
    public VenueDtoResponse update(long id, VenueDtoRequest venueDtoRequest) {

        // Buscar venue dentro de la lista
        Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(id);

        // Si no existe, lanza lanza la excepción personalizada
        if (optionalVenueEntity.isEmpty()) {
            throw new NotFoundException("Espacio con ID: " + id + " no encontrado");
        }

        VenueEntity venueEntity = optionalVenueEntity.get();

        // Reemplazar los valores
        venueEntity.setNameVenue(venueDtoRequest.nameVenue());
        venueEntity.setAddressVenue(venueDtoRequest.addressVenue());
        venueEntity.setCityVenue(venueDtoRequest.cityVenue());
        venueEntity.setCapacityVenue(venueDtoRequest.capacityVenue());

        // Devolver como Dto Response
        return venueMapper.toResponse(venueEntity);
    }

    // PATCH
    @Override
    public VenueDtoResponse partialUpdate(long id, VenueDtoRequest venueDtoRequest) {

        // Buscar venue dentro de la lista
        Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(id);

        // Si no existe, lanza lanza la excepción personalizada
        if (optionalVenueEntity.isEmpty()) {
            throw new NotFoundException("Espacio con ID: " + id + " no encontrado");
        }

        VenueEntity venueEntity = optionalVenueEntity.get();

        // Reemplazar los valores
        if (venueDtoRequest.nameVenue() != null) venueEntity.setNameVenue(venueDtoRequest.nameVenue());
        if (venueDtoRequest.addressVenue() != null) venueEntity.setAddressVenue(venueDtoRequest.addressVenue());
        if (venueDtoRequest.cityVenue() != null) venueEntity.setCityVenue(venueDtoRequest.cityVenue());
        if (venueDtoRequest.capacityVenue() != null && venueDtoRequest.capacityVenue() > 0) venueEntity.setCapacityVenue(venueDtoRequest.capacityVenue());

        // Devolver como Dto Response
        return venueMapper.toResponse(venueEntity);
    }

    // DELETE BY ID
    @Override
    public void deleteById(long id) {
        // Buscar si existe
        Optional<VenueEntity> optionalVenueEntity = venueJpaRepository.findById(id);
        if (optionalVenueEntity.isEmpty()) throw new NotFoundException("Espacio con ID: " + id + " no encontrado");

        // Si lo encuentra, lo elimina
        venueJpaRepository.deleteById(id);
    }

    // DELETE ALL
    @Override
    public void deleteAll() {
        venueJpaRepository.deleteAll();
    }
}
