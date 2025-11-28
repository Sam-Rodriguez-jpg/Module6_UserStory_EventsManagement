package com.example.demo.infrastructure.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TranceIdFilter extends OncePerRequestFilter {

    private static final String TRANCE_ID_KEY = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            // Si no viene un traceId del cliente, generamos uno.
            String traceId = UUID.randomUUID().toString();

            // Guardamos el traceId en MDC (contexto de logging)
            MDC.put(TRANCE_ID_KEY, traceId);

            // También lo exponemos en la respuesta HTTP
            response.addHeader("X-Trace-Id", traceId);

            // Continua la cadena de filtros
            filterChain.doFilter(request, response);
        } finally {
            // Limpia el MDC para que no se mezcle entre threads
            MDC.remove(TRANCE_ID_KEY);
        }
    }
}
