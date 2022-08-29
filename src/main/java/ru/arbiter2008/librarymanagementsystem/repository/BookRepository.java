package ru.arbiter2008.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arbiter2008.librarymanagementsystem.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
