package com.example.demo.infrastructure.config;

import com.example.demo.application.usecases.venue.*;
import com.example.demo.domain.ports.in.venue.*;
import com.example.demo.domain.ports.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VenueBeanConfig {

    @Bean
    public GetAllVenuesUseCaseInterface getAllVenuesUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new GetAllVenuesUseCaseImplement(venueRepositoryPort);
    }

    @Bean
    public GetVenueByIdUseCaseInterface getVenueByIdUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new GetVenueByIdUseCaseImplement(venueRepositoryPort);
    }

    @Bean
    public CreateVenueUseCaseInterface createVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new CreateVenueUseCaseImplement(venueRepositoryPort);
    }

    @Bean
    public UpdateVenueUseCaseInterface updateVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new UpdateVenueUseCaseImplement(venueRepositoryPort);
    }

    @Bean
    public PartialUpdateVenueUseCaseInterface partialUpdateVenueUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new PartialUpdateVenueUseCaseImplement(venueRepositoryPort);
    }

    @Bean
    public DeleteVenueByIdUseCaseInterface deleteVenueByIdUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new DeleteVenueByIdUseCaseImplement(venueRepositoryPort);
    }

    @Bean
    public DeleteAllVenuesUseCaseInterface deleteAllVenuesUseCase(VenueRepositoryPort venueRepositoryPort) {
        return new DeleteAllVenuesUseCaseImplement(venueRepositoryPort);
    }
}
