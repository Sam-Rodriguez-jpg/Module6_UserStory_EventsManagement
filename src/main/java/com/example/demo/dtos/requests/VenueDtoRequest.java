package com.example.demo.dtos.requests;

public record VenueDtoRequest(
        Long idVenue,
        String nameVenue,
        String addressVenue,
        String cityVenue,
        Integer capacityVenue
) {}
