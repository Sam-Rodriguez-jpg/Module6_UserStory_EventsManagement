package com.example.demo.infrastructure.adapters.in.web.dtos.requests;

import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.domain.validation.CreateValidation;
import com.example.demo.domain.validation.PatchValidation;
import com.example.demo.domain.validation.UpdateValidation;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDtoRequest {

    @NotBlank(message = "{event.name.notblank}", groups = {CreateValidation.class, UpdateValidation.class})
    @Size(min = 1, max = 255, message = "{event.name.size}", groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private String nameEvent;

    private String descriptionEvent;

    @NotNull(message = "{event.datetime.notnull}", groups = {CreateValidation.class, UpdateValidation.class})
    @Future(message = "{event.datetime.future}", groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private LocalDateTime datetimeEvent;

    @NotNull(message = "{event.duration.notnull}", groups = {CreateValidation.class, UpdateValidation.class})
    @Positive(message = "{event.duration.positive}", groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private Integer durationEvent;

    @NotNull(message = "{event.price.notnull}", groups = {CreateValidation.class, UpdateValidation.class})
    @Positive(message = "{event.price.positive}", groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private Double priceEvent;

    @NotNull(message = "{event.status.notnull}", groups = {CreateValidation.class, UpdateValidation.class})
    private StatusEventEnum statusEvent;

    @NotNull(message = "{event.venue.notnull}", groups = {CreateValidation.class, UpdateValidation.class})
    private Long idVenue;
}
