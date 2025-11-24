package com.example.demo.dtos.responses;

public record VenueDtoResponse(
   Long idVenue,
   String nameVenue,
   String addressVenue,
   String cityVenue,
   Integer capacityVenue
) {}
