package com.example.demo.infrastructure.adapters.in.web.dtos.requests;

public record VenueDtoRequest(
        Long idVenue,
        String nameVenue,
        String addressVenue,
        String cityVenue,
        Integer capacityVenue
) {}
