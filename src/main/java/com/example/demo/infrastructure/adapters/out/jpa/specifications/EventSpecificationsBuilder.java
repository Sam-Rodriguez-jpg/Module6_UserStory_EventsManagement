package com.example.demo.infrastructure.adapters.out.jpa.specifications;

import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecificationsBuilder {
    public static Specification<EventEntity> build(
            StatusEventEnum statusEvent,
            Long idVenue,
            LocalDateTime datetimeEvent,
            String cityVenue
    ) {
        Specification<EventEntity> specification = EventStatusSpecification.hasStatus(statusEvent)
                        .and(EventVenueSpecification.hasVenue(idVenue))
                        .and(EventDateSpecification.hasDate(datetimeEvent))
                        .and(EventCitySpecification.hasCity(cityVenue));

        return specification;
    }
}
