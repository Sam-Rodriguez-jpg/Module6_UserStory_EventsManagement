package com.example.demo.infrastructure.adapters.in.web.dtos.requests;

import com.example.demo.domain.validation.CreateValidation;
import com.example.demo.domain.validation.PatchValidation;
import com.example.demo.domain.validation.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class VenueDtoRequest {

    @NotBlank(message = "{venue.name.notblank}", groups = {CreateValidation.class, UpdateValidation.class})
    @Size(message = "venue.name.size{}", min = 1, max = 255, groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private String nameVenue;

    @NotBlank(message = "{venue.address.notblank}", groups = {CreateValidation.class, UpdateValidation.class})
    @Size(message = "{venue.address.size}", min = 1, max = 255, groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private String addressVenue;

    @NotBlank(message = "{venue.city.notblank}", groups = {CreateValidation.class, UpdateValidation.class})
    @Size(message = "{venue.city.size}", min = 1, max = 255, groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private String cityVenue;

    @NotNull(message = "venue.capacity.notnull", groups = {CreateValidation.class, UpdateValidation.class})
    @Positive(message = "{venue.capacity.positive}", groups = {CreateValidation.class, UpdateValidation.class, PatchValidation.class})
    private Integer capacityVenue;
}
