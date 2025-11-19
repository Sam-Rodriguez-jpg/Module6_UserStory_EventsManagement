package com.example.demo.services.interfaces;

import com.example.demo.dtos.requests.VenueDtoRequest;
import com.example.demo.dtos.responses.EventDtoResponse;
import com.example.demo.dtos.responses.VenueDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VenueServiceInterface {
    // CRUD
    List<VenueDtoResponse> findAll();
    VenueDtoResponse findById(long id);
    VenueDtoResponse create(VenueDtoRequest venueDtoRequest);
    VenueDtoResponse update(long id, VenueDtoRequest venueDtoRequest);
    VenueDtoResponse partialUpdate(long id, VenueDtoRequest venueDtoRequest);
    void deleteById(long id);
    void deleteAll();
}
