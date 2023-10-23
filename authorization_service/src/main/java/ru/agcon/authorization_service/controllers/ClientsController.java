package ru.agcon.authorization_service.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.agcon.authorization_service.dto.AuthenticationDTO;
import ru.agcon.authorization_service.dto.ClientsDTO;
import ru.agcon.authorization_service.models.Clients;
import ru.agcon.authorization_service.security.JWTUtil;
import ru.agcon.authorization_service.services.ClientsService;
import ru.agcon.authorization_service.services.RegistrationService;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class ClientsController {

    private static int counter = 0;
    private final ClientsService clientsService;
    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ClientsController(ClientsService clientsService, RegistrationService registrationService, ModelMapper modelMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.clientsService = clientsService;
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/validate")
    @ResponseBody
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        try {
            String login = jwtUtil.validateTokenAndRetrieveClaim(token);
            return ResponseEntity.ok("Token is valid. User login: " + login);
        } catch (JWTVerificationException e) {
            return ResponseEntity.badRequest().body("Invalid token. " + e.getMessage());
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createClient(@RequestBody ClientsDTO clientsDTO) {
        Clients client = convertToClients(clientsDTO);
        if (counter < 1){
            client.setRole("ADMIN");
            counter++;
        }
        else client.setRole("USER");
        registrationService.create(client);
        String token = jwtUtil.generateToken(client.getLogin());
        return ResponseEntity.ok(Map.of("jwt-token", token));
    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Incorrect credentials!"));
        }

        String token = jwtUtil.generateToken(authenticationDTO.getLogin());
        return ResponseEntity.ok(Map.of("jwt-token", token));
    }

    @GetMapping(path = "/success")
    public Authentication auth(@RequestHeader String authorization) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public Clients convertToClients(ClientsDTO clientsDTO){
        return modelMapper.map(clientsDTO, Clients.class);
    }
}