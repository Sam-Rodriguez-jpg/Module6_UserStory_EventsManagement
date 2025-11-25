package com.example.demo.infrastructure.adapters.in.web.dtos.responses;

public record VenueDtoResponse(
   Long idVenue,
   String nameVenue,
   String addressVenue,
   String cityVenue,
   Integer capacityVenue
) {}
