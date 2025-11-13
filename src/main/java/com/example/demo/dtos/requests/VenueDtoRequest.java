package com.example.demo.dtos.requests;

public record VenueDtoRequest(
        String nameVenue,
        String addressVenue,
        String cityVenue,
        Integer capacityVenue
) {}
