package ru.agcon.marketplace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agcon.marketplace.models.Clients;
import ru.agcon.marketplace.repository.ClientsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping("")
    public ResponseEntity<List<Clients>> getAllClients() {
        List<Clients> clients = clientsRepository.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable(value = "id") int clientId) {
        Optional<Clients> client = clientsRepository.findById(clientId);
        if (client.isPresent()) {
            return ResponseEntity.ok().body(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Clients createClient(@RequestBody Clients client) {
        return clientsRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateClient(@PathVariable(value = "id") int clientId,
                                                @RequestBody Clients clientDetails) {
        Optional<Clients> optionalClient = clientsRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Clients client = optionalClient.get();
            client.setEmail(clientDetails.getEmail());
            client.setLogin(clientDetails.getLogin());
            client.setPassword(clientDetails.getPassword());
            client.setName(clientDetails.getName());
            return ResponseEntity.ok(clientsRepository.save(client));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable(value = "id") int clientId) {
        Optional<Clients> optionalClient = clientsRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            clientsRepository.delete(optionalClient.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}