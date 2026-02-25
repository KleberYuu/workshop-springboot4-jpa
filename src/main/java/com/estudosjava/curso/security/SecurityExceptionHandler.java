package com.estudosjava.curso.security;

import com.estudosjava.curso.resources.exceptions.StandardError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public SecurityExceptionHandler() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());

    }

    private void writeJsonError(
            HttpServletResponse response,
            int status,
            String title,
            String detail,
            String path
    ) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");

        if (status == HttpStatus.UNAUTHORIZED.value()) {
            response.setHeader("WWW-Authenticate",
                    "Bearer error=\"invalid_token\", error_description=\"" + detail + "\"");
        }

        StandardError error = new StandardError(
                Instant.now(),
                status,
                title,
                detail,
                path
        );

        response.getWriter().write(objectMapper.writeValueAsString(error));
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        String detail = switch (authException.getClass().getSimpleName()) {
            case "ExpiredJwtException"      -> "Expired token";
            case "MalformedJwtException"    -> "Malformed or invalid token";
            case "SignatureException"       -> "Invalid token signature";
            case "UnsupportedJwtException"  -> "Unsupported token";
            case "BadCredentialsException"  -> "Bad credentials";
            default                         -> "Invalid or missing token";
        };

        writeJsonError(
                response,
                HttpStatus.UNAUTHORIZED.value(),
                "Authentication failed",
                detail,
                request.getRequestURI()
        );
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        writeJsonError(
                response,
                HttpStatus.FORBIDDEN.value(),
                "Access denied",
                "You do not have permission to access this resource",
                request.getRequestURI()
        );
    }
}
