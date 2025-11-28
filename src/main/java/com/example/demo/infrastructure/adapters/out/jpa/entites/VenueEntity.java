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

import java.util.ArrayList;
import java.util.List;

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

    private String nameVenue;

    private String addressVenue;

    private String cityVenue;

    private Integer capacityVenue;

    @OneToMany(
            mappedBy = "venueEntity", // Apunta al atributo ManyToOne del evento
            cascade = CascadeType.ALL, // Si se guardan venues, se guardan eventos
            orphanRemoval = true, // Si se quita un venue, se eliminan los eventos con ese venue
            fetch = FetchType.LAZY // No cargar eventos automáticamente
    )
    private List<EventEntity> eventsList = new ArrayList<>();

    // Metodos para mejorar la consistencia del ciclo de vida
    public void addEvent(EventEntity eventEntity) {
        eventsList.add(eventEntity);
        eventEntity.setVenueEntity(this);
    }

    public void removeEvent(EventEntity eventEntity) {
        eventsList.remove(eventEntity);
        eventEntity.setVenueEntity(null);
    }
}
