package com.example.demo.domain.models;

import com.example.demo.domain.exceptions.InputBlankException;
import com.example.demo.domain.exceptions.InvalidValueException;
import com.example.demo.domain.models.enums.StatusEventEnum;

import java.time.LocalDateTime;

public record EventModel(
        Long idEvent,
        String nameEvent,
        String descriptionEvent,
        LocalDateTime datetimeEvent,
        Integer durationEvent,
        Double priceEvent,
        StatusEventEnum statusEvent,
        Long idVenue
) {
    private void validateNotBlank(String inputValue, String fieldName) {
        if (inputValue == null || inputValue.isBlank()) {
            throw new InputBlankException("No puedes dejar el campo '" + fieldName + "' vacío.");
        }
    }

    private void validateNotNull(Object inputValue, String fieldName) {
        if (inputValue == null) {
            throw new InputBlankException("No puedes dejar el campo '" + fieldName + "' vacío.");
        }
    }

    private void validatePositive(Number inputValue, String fieldName) {
        if (inputValue == null) {
            throw new InputBlankException("No puedes dejar el campo '" + fieldName + "' vacío.");
        }
        if (inputValue.doubleValue() <= 0) {
            throw new InvalidValueException("El campo '" + fieldName + "' debe ser mayor que 0.");
        }
    }

    public EventModel {
        validateNotBlank(nameEvent, "Nombre del evento");
        validateNotNull(datetimeEvent, "Fecha y hora del evento");
        validatePositive(durationEvent, "Duración del evento");
        validatePositive(priceEvent, "Precio de entrada del evento");
        validateNotNull(statusEvent, "Estado del evento");
        validatePositive(idVenue, "Espacio donde será el evento");
    }
}
