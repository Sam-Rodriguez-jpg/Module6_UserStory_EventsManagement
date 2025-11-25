package com.example.demo.infrastructure.config;

import com.example.demo.application.usecases.event.*;
import com.example.demo.domain.ports.in.events.*;
import com.example.demo.domain.ports.out.EventRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBeanConfig {

    @Bean
    public GetAllEventsUseCaseInterface getAllEventsUseCase(EventRepositoryPort eventRepositoryPort) {
        return new GetAllEventsUseCaseImplement(eventRepositoryPort);
    }

    @Bean
    public GetEventByIdUseCaseInterface getEventByIdUseCase(EventRepositoryPort eventRepositoryPort) {
        return new GetEventByIdUseCaseImplement(eventRepositoryPort);
    }

    @Bean
    public CreateEventUseCaseInterface createEventUseCase(EventRepositoryPort eventRepositoryPort) {
        return new CreateEventUseCaseImplement(eventRepositoryPort);
    }

    @Bean
    public UpdateEventUseCaseInterface updateEventUseCase(EventRepositoryPort eventRepositoryPort) {
        return new UpdateEventUseCaseImplement(eventRepositoryPort);
    }

    @Bean
    public PartialUpdateEventUseCaseInterface partialUpdateEventUseCase(EventRepositoryPort eventRepositoryPort) {
        return new PartialUpdateEventUseCaseImplement(eventRepositoryPort);
    }

    @Bean
    public DeleteEventByIdUseCaseInterface deleteEventByIdUseCase(EventRepositoryPort eventRepositoryPort) {
        return new DeleteEventByIdUseCaseImplement(eventRepositoryPort);
    }

    @Bean
    public DeleteAllEventsUseCaseInterface deleteAllEventsUseCase(EventRepositoryPort eventRepositoryPort) {
        return new DeleteAllEventsUseCaseImplement(eventRepositoryPort);
    }
}
