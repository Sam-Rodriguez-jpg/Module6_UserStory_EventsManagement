package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueEntity {
    private Long idVenue;
    private String nameVenue;
    private String addressVenue;
    private String cityVenue;
    private Integer capacityVenue;
}
