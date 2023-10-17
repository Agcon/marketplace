package ru.agcon.authorization_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agcon.authorization_service.models.Clients;
import ru.agcon.authorization_service.repositories.ClientsRepository;
import ru.agcon.authorization_service.security.ClientsDetails;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = false)
public class ClientsService implements UserDetailsService {
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

    @Transactional(readOnly = true)
    public Set<String> getAllKeys(){
        return clientsRepository.getAllKeys();
    }

    public void delete(String key){
        clientsRepository.delete(key);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Clients> client = clientsRepository.findByLogin(username);

        if (client.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new ClientsDetails(client.get());
    }
}
