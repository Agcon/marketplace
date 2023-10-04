package ru.agcon.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agcon.marketplace.models.WashingMachines;

@Repository
public interface WashingMachinesRepository extends JpaRepository<WashingMachines, Integer> {
}
