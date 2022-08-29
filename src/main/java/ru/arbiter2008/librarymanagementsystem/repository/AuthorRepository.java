package ru.arbiter2008.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arbiter2008.librarymanagementsystem.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
