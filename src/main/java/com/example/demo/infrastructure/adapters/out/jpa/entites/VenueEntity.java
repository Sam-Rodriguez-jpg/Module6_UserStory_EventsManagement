package com.example.demo.infrastructure.adapters.out.jpa.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venues")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenue")
    private Long idVenue;

    @NotBlank(message = "No puedes dejar este campo vacío")
    @Size(min = 1, max = 255, message = "Este campo puede tener entre 1 y 255 caracteres")
    private String nameVenue;

    @NotBlank
    @Size(min = 1, max = 255, message = "Este campo puede tener entre 1 y 255 caracteres")
    private String addressVenue;


    @NotBlank
    @Size(min = 1, max = 255, message = "Este campo puede tener entre 1 y 255 caracteres")
    private String cityVenue;

    @NotNull
    @Positive
    private Integer capacityVenue;
}
