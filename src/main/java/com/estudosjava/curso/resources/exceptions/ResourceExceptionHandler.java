package com.estudosjava.curso.resources.exceptions;

import com.estudosjava.curso.services.exceptions.BusinessException;
import com.estudosjava.curso.services.exceptions.DatabaseException;
import com.estudosjava.curso.services.exceptions.DuplicateResourceException;
import com.estudosjava.curso.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> handleBusiness(BusinessException e, HttpServletRequest request) {

        String error = "Business rule violation";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = "Validation error";

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        String message = "Validation failed for " + fieldErrors.size() + " field(s).";

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                message,
                request.getRequestURI(),
                fieldErrors);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> generic(Exception e, HttpServletRequest request) {
        String error = "Unexpected error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<StandardError> duplicate(DuplicateResourceException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.CONFLICT;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Duplicate resource",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> unauthorized(
            BadCredentialsException e,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Authentication error",
                "Username does not exist or password is invalid",
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> handleAccessDenied(
            AccessDeniedException e,
            HttpServletRequest request) {

        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.FORBIDDEN.value(),
                "Access denied",
                "You do not have permission to access this resource",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
}
