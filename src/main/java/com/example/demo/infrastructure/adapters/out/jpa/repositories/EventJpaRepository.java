package com.example.demo.infrastructure.adapters.out.jpa.repositories;

import com.example.demo.infrastructure.adapters.out.jpa.entites.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {}
