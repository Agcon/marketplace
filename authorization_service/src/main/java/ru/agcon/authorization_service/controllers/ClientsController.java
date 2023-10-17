package ru.agcon.authorization_service.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agcon.authorization_service.dto.ClientsDTO;
import ru.agcon.authorization_service.models.Clients;
//import ru.agcon.authorization_service.security.JWTUtil;
import ru.agcon.authorization_service.services.ClientsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    //private final JWTUtil jwtUtil;

    @Autowired
    public ClientsController(ClientsService clientsService, ModelMapper modelMapper) {
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        //this.jwtUtil = jwtUtil;
    }

    @GetMapping("")
    public ResponseEntity<List<ClientsDTO>> getAllClients() {
        List<Clients> clients = clientsService.getAll();
        List<ClientsDTO> clientsDTOS = new ArrayList<>();
        for (Clients client: clients) {
            clientsDTOS.add(convertToClientsDTO(client));
        }
        return ResponseEntity.ok(clientsDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientsDTO> getClientById(@PathVariable(value = "login") String clientLogin) {
        Optional<Clients> client = clientsService.findOne(clientLogin);
        if (client.isPresent()) {
            ClientsDTO clientsDTO = convertToClientsDTO(client.get());
            return ResponseEntity.ok().body(clientsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public void createClient(@RequestBody ClientsDTO clientsDTO) {
        Clients client = convertToClients(clientsDTO);
        client.setRole("USER");
        clientsService.create(client);
    }

    @PutMapping("/{id}")
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
            clientsService.create(client);
            return ResponseEntity.ok("Ok");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
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