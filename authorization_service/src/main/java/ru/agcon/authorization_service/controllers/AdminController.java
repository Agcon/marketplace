package ru.agcon.authorization_service.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import ru.agcon.authorization_service.dto.ClientsDTO;
import ru.agcon.authorization_service.models.Clients;
import ru.agcon.authorization_service.security.JWTUtil;
import ru.agcon.authorization_service.services.ClientsService;
import ru.agcon.authorization_service.services.RegistrationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ClientsService clientsService;
    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AdminController(ClientsService clientsService, RegistrationService registrationService, ModelMapper modelMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<String>> getKeys(){
        return ResponseEntity.ok(clientsService.getAllKeys());
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

    public ClientsDTO convertToClientsDTO(Clients clients){
        return modelMapper.map(clients, ClientsDTO.class);
    }
}
