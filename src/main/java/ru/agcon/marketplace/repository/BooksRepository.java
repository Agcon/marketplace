package ru.agcon.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agcon.marketplace.models.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
}
