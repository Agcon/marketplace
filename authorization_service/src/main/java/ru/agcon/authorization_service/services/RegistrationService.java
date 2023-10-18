package ru.agcon.authorization_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agcon.authorization_service.models.Clients;
import ru.agcon.authorization_service.repositories.ClientsRepository;

@Service
public class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final ClientsRepository clientsRepository;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, ClientsRepository clientsRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clientsRepository = clientsRepository;
    }

    @Transactional
    public void create(Clients client){
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientsRepository.save(client.getLogin(), client);
    }
}
