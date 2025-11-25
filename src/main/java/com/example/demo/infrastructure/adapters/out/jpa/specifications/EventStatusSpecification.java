package com.example.demo.infrastructure.adapters.out.jpa.specifications;

import com.example.demo.domain.models.enums.StatusEventEnum;
import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.domain.Specification;

public class EventStatusSpecification {
    public static Specification<EventEntity> hasStatus(StatusEventEnum statusEvent) {
        return ((root, query, criteriaBuilder) -> {
            if (statusEvent == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("statusEvent"), statusEvent);
        });
    }
}
