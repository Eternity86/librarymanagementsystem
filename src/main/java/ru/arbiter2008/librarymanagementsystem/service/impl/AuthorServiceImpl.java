package ru.arbiter2008.librarymanagementsystem.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.arbiter2008.librarymanagementsystem.model.Author;
import ru.arbiter2008.librarymanagementsystem.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Override
    public List<Author> findAllAuthors() {
        return null;
    }

    @Override
    public Author findAuthorById(Long id) {
        return null;
    }

    @Override
    public void createAuthor(Author author) {

    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Long id) {

    }

    @Override
    public Page<Author> findPaginated(Pageable pageable) {
        return null;
    }

}
