package ru.agcon.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agcon.marketplace.models.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer> {
}
