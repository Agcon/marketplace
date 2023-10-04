package ru.agcon.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agcon.marketplace.models.Phones;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, Integer> {
}
