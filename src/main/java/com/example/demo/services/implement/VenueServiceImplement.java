package com.example.demo.services.implement;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.VenueDtoResponse;
import com.example.demo.entities.VenueEntity;
import com.example.demo.exceptions.custom.BadRequestException;
import com.example.demo.exceptions.custom.NoContentException;
import com.example.demo.exceptions.custom.NotFoundException;
import com.example.demo.mappers.VenueMapper;
import com.example.demo.repositories.implement.VenueRepositoryImplement;
import com.example.demo.services.interfaces.VenueServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueServiceImplement implements VenueServiceInterface {

    // Database
    private final VenueRepositoryImplement venueRepository = new VenueRepositoryImplement();

    // Mapper para convertir entre Dto y Entity
    private final VenueMapper venueMapper = new VenueMapper();

    // GET ALL
    @Override
    public List<VenueDtoResponse> getAllVenues() {

        // Obtener todos los venues del repository
        List<VenueEntity> venueEntitiesList = venueRepository.getAllVenues();

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
    public VenueDtoResponse getVenueById(long id) {
        // Buscar venue dentro de la lista
        VenueEntity venueEntity = venueRepository.getVenueById(id);

        // Si no existe, lanza lanza la excepción personalizada
        if (venueEntity == null) {
            throw new NotFoundException("Venue con ID: " + id + " no encontrado");
        }

        // Si existe, convertir a Dto Response y devolver
        return venueMapper.toResponse(venueEntity);
    }


    // POST
    @Override
    public VenueDtoResponse createVenue(VenueDtoRequest venueDtoRequest) {

        if (venueDtoRequest.nameVenue() == null || venueDtoRequest.cityVenue() == null || venueDtoRequest.addressVenue() == null) throw new BadRequestException("No puedes dejar campos vacíos");

        // Se convierte de Dto Request a Entity
        VenueEntity venueEntity = venueMapper.toEntity(venueDtoRequest);

        // Se guarda en la lista
        VenueEntity savedVenue = venueRepository.createVenue(venueEntity);

        // Se convierte de Entity a Dto Response y lo devuelve
        return venueMapper.toResponse(savedVenue);
    }

    // PUT
    @Override
    public VenueDtoResponse updateVenue(long id, VenueDtoRequest venueDtoRequest) {

        // Buscar si existe
        VenueEntity venueEntity = venueRepository.getVenueById(id);
        if (venueEntity == null) throw new NotFoundException("Venue con ID: " + id + " no encontrado");

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
    public VenueDtoResponse partialUpdateVenue(long id, VenueDtoRequest venueDtoRequest) {

        // Buscar si existe
        VenueEntity venueEntity = venueRepository.getVenueById(id);
        if (venueEntity == null) throw new NotFoundException("Venue con ID: " + id + " no encontrado");

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
    public void deleteVenueById(long id) {
        // Buscar si existe
        VenueEntity venueEntity = venueRepository.getVenueById(id);
        if (venueEntity == null) throw new NotFoundException("Venue con ID: " + id + " no encontrado");

        // Si lo encuentra, lo elimina
        venueRepository.deleteVenue(id);
    }

    // DELETE ALL
    @Override
    public void deleteAllVenues() {
        venueRepository.deleteAllVenues();
    }
}
