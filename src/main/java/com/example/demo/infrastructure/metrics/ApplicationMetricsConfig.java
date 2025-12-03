package com.example.demo.infrastructure.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMetricsConfig {

    private final Counter totalRequestsCounter;
    private final Counter errorRequestsCounter;

    public ApplicationMetricsConfig(MeterRegistry registry) {
        this.totalRequestsCounter = Counter
                .builder("app.requests.total")
                .description("Total number of API requests")
                .register(registry);

        this.errorRequestsCounter = Counter
                .builder("app.requests.errors")
                .description("Total number of failed API requests")
                .register(registry);
    }

    public void incrementRequests() {
        totalRequestsCounter.increment();
    }

    public void incrementErrors() {
        errorRequestsCounter.increment();
    }
}
