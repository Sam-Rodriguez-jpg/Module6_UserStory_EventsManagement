package com.example.demo.infrastructure.adapters.out.jpa.specifications;

import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.domain.Specification;

public class EventCitySpecification {
    public static Specification<EventEntity> hasCity(String cityVenue) {
        return ((root, query, criteriaBuilder) -> {
            if (cityVenue == null || cityVenue.isBlank()) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("venueEntity").get("cityVenue"), cityVenue);

            // root: Representa la tabla principal de la query, donde se accede a los atributos
            // query: Lo que se va a generar
            // cB: Son las queries SQL internas, ejemplo: WHERE columna = valor;
        });
    }
}
