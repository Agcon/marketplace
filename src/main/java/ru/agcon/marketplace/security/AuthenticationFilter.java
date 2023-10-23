package ru.agcon.marketplace.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;

import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    public AuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String sourceUrl = "http://localhost:8081/auth/success";

            HttpMethod sourceMethod = HttpMethod.GET;
            HttpHeaders sourceHeaders = new HttpHeaders();
            sourceHeaders.set("Authorization", request.getHeader("Authorization"));
            HttpEntity<?> sourceRequestEntity = new HttpEntity<>(sourceHeaders);
            ResponseEntity<String> sourceResponseEntity = new RestTemplate().exchange(sourceUrl, sourceMethod, sourceRequestEntity, String.class);

            Authentication authentication = AuthenticationConverter.convert(sourceResponseEntity.getBody());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }
}