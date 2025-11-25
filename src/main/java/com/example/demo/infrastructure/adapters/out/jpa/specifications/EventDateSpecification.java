package com.example.demo.infrastructure.adapters.out.jpa.specifications;

import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventDateSpecification {
    public static Specification<EventEntity> hasDate(LocalDateTime datetime) {
        return ((root, query, criteriaBuilder) -> {
            if (datetime == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("datetimeEvent"), datetime);
        });
    }
}
