package ru.agcon.authorization_service.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClientsDTO>> getAllClients() {
        List<Clients> clients = clientsService.getAll();
        List<ClientsDTO> clientsDTOS = new ArrayList<>();
        for (Clients client: clients) {
            clientsDTOS.add(convertToClientsDTO(client));
        }
        return ResponseEntity.ok(clientsDTOS);
    }

    @GetMapping("/{login}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientsDTO> getClientByLogin(@PathVariable(value = "login") String clientLogin) {
        Optional<Clients> client = clientsService.findOne(clientLogin);
        if (client.isPresent()) {
            ClientsDTO clientsDTO = convertToClientsDTO(client.get());
            return ResponseEntity.ok().body(clientsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/keys")
    public ResponseEntity<Set<String>> getKeys(){
        return ResponseEntity.ok(clientsService.getAllKeys());
    }

    @PostMapping("/register")
    public Map<String, String> createClient(@RequestBody ClientsDTO clientsDTO) {
        Clients client = convertToClients(clientsDTO);
        client.setRole("ADMIN");
        registrationService.create(client);
        String token = jwtUtil.generateToken(client.getLogin());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getLogin());
        return Map.of("jwt-token", token);
    }

    @PutMapping("/{login}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateClient(@PathVariable(value = "login") String clientLogin,
                                               @RequestBody Clients clientDetails) {
        Optional<Clients> optionalClient = clientsService.findOne(clientLogin);
        if (optionalClient.isPresent()) {
            Clients client = optionalClient.get();
            client.setEmail(clientDetails.getEmail());
            client.setLogin(clientDetails.getLogin());
            client.setRole(clientDetails.getRole());
            client.setPassword(clientDetails.getPassword());
            client.setName(clientDetails.getName());
            registrationService.create(client);
            return ResponseEntity.ok("Ok");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{login}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable(value = "login") String clientLogin) {
        Optional<Clients> optionalClient = clientsService.findOne(clientLogin);
        if (optionalClient.isPresent()) {
            clientsService.delete(optionalClient.get().getLogin());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Clients convertToClients(ClientsDTO clientsDTO){
        return modelMapper.map(clientsDTO, Clients.class);
    }

    public ClientsDTO convertToClientsDTO(Clients clients){
        return modelMapper.map(clients, ClientsDTO.class);
    }
}