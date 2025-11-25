package com.example.demo.infrastructure.adapters.out.jpa.specifications;

import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.domain.Specification;

public class EventVenueSpecification {
    public static Specification<EventEntity> hasVenue(Long idVenue) {
        return ((root, query, criteriaBuilder) -> {
            if (idVenue == null) return null;
            return criteriaBuilder.equal(root.get("venueEntity").get("idVenue"), idVenue);
        });
    }
}
