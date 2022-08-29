package ru.arbiter2008.librarymanagementsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.arbiter2008.librarymanagementsystem.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthors();

    Author findAuthorById(Long id);

    void createAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(Long id);

    Page<Author> findPaginated(Pageable pageable);

}
