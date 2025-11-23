package com.example.demo.domain.models;

import com.example.demo.domain.exceptions.InputBlankException;
import com.example.demo.domain.exceptions.InvalidValueException;

public record VenueModel(
        Long idVenue,
        String nameVenue,
        String addressVenue,
        String cityVenue,
        Integer capacityVenue
) {
    private void validateNotBlank(String inputValue, String fieldName) {
        if (inputValue == null || inputValue.isBlank()) {
            throw new InputBlankException("El campo: '" + fieldName + "' no puede estar vacío");
        }
    }

    public VenueModel {
        validateNotBlank(nameVenue, "Nombre del lugar del evento");
        validateNotBlank(addressVenue, "Dirección del lugar del evento");
        validateNotBlank(cityVenue, "Ciudad donde será el lugar del evento");

        if (capacityVenue == null) {
            throw new InputBlankException("El campo: 'Capacidad del lugar del evento' no puede estar vacío");
        }
        if (capacityVenue <= 0) {
            throw new InvalidValueException("La capacidad debe ser mayor que 0. Valor recibido: " + capacityVenue);
        }
    }
}
