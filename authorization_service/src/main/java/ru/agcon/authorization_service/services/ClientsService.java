package ru.agcon.authorization_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agcon.authorization_service.models.Clients;
import ru.agcon.authorization_service.repositories.ClientsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
public class ClientsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Transactional(readOnly = true)
    public List<Clients> getAll(){
        return clientsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Clients> findOne(String login){
        return clientsRepository.findByLogin(login);
    }

    public void create(Clients client){
        clientsRepository.save(client.getLogin(), client);
    }

    public void delete(String key){
        clientsRepository.delete(key);
    }
}
