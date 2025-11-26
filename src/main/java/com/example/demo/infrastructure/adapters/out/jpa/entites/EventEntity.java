package com.example.demo.infrastructure.adapters.out.jpa.entites;

import com.example.demo.domain.models.enums.StatusEventEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    @NotBlank
    @Size(min = 1, max = 255, message = "Este campo puede tener entre 1 y 255 caracteres")
    private String nameEvent;

    private String descriptionEvent;

    @NotNull
    @Future
    private LocalDateTime datetimeEvent;

    @NotNull
    @Positive
    private Integer durationEvent;

    @NotNull
    @Positive
    private Double priceEvent;

    @NotNull
    private StatusEventEnum statusEvent;

    // LAZY: Evita cargar el Venue cuando cargas un Event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venue", nullable = false)
    private VenueEntity venueEntity;
}
