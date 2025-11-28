package com.example.demo.infrastructure.adapters.in.web.exceptions;

import com.example.demo.domain.exceptions.ConflictException;
import com.example.demo.domain.exceptions.NotContentException;
import com.example.demo.domain.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// Encargada de estar atenta a cualquier excepción que pueda ocurrir y devolverla en el formato RFC 7807
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
    Método interno que construye un objeto ProblemDetail estándar donde se configura el:
    - status
    - detail (Mensaje)
     - title (Mensaje corto asociado al status)
     - instance (URL del endpoint donde ocurrio el error)
     - timestamp
     - traceId (un ID unico para rastreo)
     */
    private ProblemDetail buildProblemDetail(
            HttpStatus status,
            String detail,
            HttpServletRequest request
    ) {
        // Construye el ProblemDetail base
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);

        // Título basado en la frase estándar del status
        problemDetail.setTitle(status.getReasonPhrase());

        // Ruta del endpoint que genero el error
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        // Momento en el que ocurrió el error
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // traceId real del MDC (mismo que imprime en consola)
        String traceId = MDC.get("traceId");

        // ID único para correlación de logs
        problemDetail.setProperty("traceId",
                traceId != null ? traceId : UUID.randomUUID().toString());

        return problemDetail;
    }

    // Excepciones pedidas
    // 400 - Bean Validator
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.warn("Validation error at {} - {} invalid fields - traceId={} - method={}",
                request.getRequestURI(),
                exception.getBindingResult().getErrorCount(),
                traceId,
                request.getMethod()
        );

        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail problemDetail = buildProblemDetail(
                HttpStatus.valueOf(400),
                "Validation field",
                request
        );

        problemDetail.setProperty("error", errors);
        return problemDetail;
    }

    // 404 - Not Found (JPA)
    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleJpaNotFoundException(
            EntityNotFoundException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.warn("JPA Entity not found at {} - traceId={} - message={}",
                request.getRequestURI(),
                traceId,
                exception.getMessage()
        );

        return buildProblemDetail(
                HttpStatus.valueOf(404),
                exception.getMessage(),
                request
        );
    }

    // 409 - Violación de constrains en DB
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityException(
            DataIntegrityViolationException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.warn("DB constraint violation at {} - traceId={} - cause={}",
                request.getRequestURI(),
                traceId,
                exception.getMostSpecificCause().getMessage()
        );

        return buildProblemDetail(
                HttpStatus.valueOf(409),
                "Database constraint violation",
                request
        );
    }

    // 400 - JSON Error
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleJsonParseException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        String cause =  exception.getMostSpecificCause() != null ?
                exception.getMostSpecificCause().getMessage() :
                exception.getMessage();

        log.warn("Malformed JSON at {} - traceId={} - cause={}",
                request.getRequestURI(),
                traceId,
                cause
        );

        return buildProblemDetail(
                HttpStatus.valueOf(400),
                "Malformed JSON request",
                request
        );
    }


    // Excepciones personalizadas
    // 204 - Not Content
    @ExceptionHandler(NotContentException.class)
    public ProblemDetail handleNotContentException(
            NotContentException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.info("No content at {} - traceId={} - message={}",
                request.getRequestURI(),
                traceId,
                exception.getMessage()
        );

        return buildProblemDetail(
                HttpStatus.valueOf(204),
                exception.getMessage(),
                request
        );
    }

    // 404 - Not Found
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(
            NotFoundException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.warn("Resource not found at {} - traceId={} - message={}",
                request.getRequestURI(),
                traceId,
                exception.getMessage()
        );

        return buildProblemDetail(
                HttpStatus.valueOf(404),
                exception.getMessage(),
                request
        );
    }

    // 409 - Conflict
    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflictException(
            ConflictException exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.warn("Conflict at {} - traceId={} - message={}",
                request.getRequestURI(),
                traceId,
                exception.getMessage()
        );


        return buildProblemDetail(
                HttpStatus.valueOf(409),
                exception.getMessage(),
                request
        );
    }

    // Cualquier excepción no controlada
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(
            Exception exception,
            HttpServletRequest request
    ) {
        String traceId = MDC.get("traceId");

        log.error("UNEXPECTED ERROR at {} - traceId={} - cause={}",
                request.getRequestURI(),
                traceId,
                exception.getMessage()
        );


        return buildProblemDetail(
                HttpStatus.valueOf(500),
                exception.getMessage(),
                request
        );
    }
}
