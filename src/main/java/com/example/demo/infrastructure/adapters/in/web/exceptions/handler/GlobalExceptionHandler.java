package com.example.demo.infrastructure.adapters.in.web.exceptions.handler;

import com.example.demo.infrastructure.adapters.in.web.exceptions.custom.BadRequestException;
import com.example.demo.infrastructure.adapters.in.web.exceptions.custom.ConflictException;
import com.example.demo.infrastructure.adapters.in.web.exceptions.custom.NoContentException;
import com.example.demo.infrastructure.adapters.in.web.exceptions.custom.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Cualquier excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>  handleGenericException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(500));
        response.put("error", "Internal Server Error");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(500));
    }

    // Excepciones custom / Controladas
    // 204 - Not Content
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Map<String, Object>> handleNotContentException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(204));
        response.put("error", "Not Content");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(204));
    }

    // 400 - Bad Request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(400));
        response.put("error", "Bad Request");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(400));
    }

    // 404 - Not Found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(404));
        response.put("error", "Not Found");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(404));
    }

    // 409 - Conflict
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> handlerConflictException(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("statusEvent", HttpStatus.valueOf(409));
        response.put("error", "Conflict");
        response.put("message", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(409));
    }
}
