package com.example.demo.services.interfaces;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.VenueDtoResponse;

import java.util.List;

public interface VenueServiceInterface {
    List<VenueDtoResponse> getAllVenues();
    VenueDtoResponse getVenueById(long id);
    VenueDtoResponse createVenue(VenueDtoRequest venueDtoRequest);
    VenueDtoResponse updateVenue(long id, VenueDtoRequest venueDtoRequest);
    VenueDtoResponse partialUpdateVenue(long id, VenueDtoRequest venueDtoRequest);
    void deleteVenueById(long id);
    void deleteAllVenues();
}
