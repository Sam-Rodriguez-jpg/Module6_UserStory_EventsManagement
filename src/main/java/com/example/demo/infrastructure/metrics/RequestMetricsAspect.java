package com.example.demo.infrastructure.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestMetricsAspect extends OncePerRequestFilter {

    private final ApplicationMetricsConfig metricsConfig;
    private final MeterRegistry registry;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        metricsConfig.incrementRequests();
        Timer.Sample sample = Timer.start(registry);

        try {
            filterChain.doFilter(request, response);

            // error = 400, 404, 500, pero sin excepción
            if (response.getStatus() >= 400) {
                metricsConfig.incrementErrors();
            }

            sample.stop(Timer.builder("app.response.time")
                    .tag("error", String.valueOf(response.getStatus() >= 400))
                    .register(registry)
            );

        } catch (Exception exception) {

            metricsConfig.incrementErrors();

            sample.stop(Timer.builder("app.response.time")
                    .tag("error", "true")
                    .register(registry)
            );

            throw exception;
        }
    }
}
