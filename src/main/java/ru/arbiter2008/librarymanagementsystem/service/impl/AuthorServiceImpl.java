package ru.arbiter2008.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.arbiter2008.librarymanagementsystem.exception.NotFoundException;
import ru.arbiter2008.librarymanagementsystem.model.Author;
import ru.arbiter2008.librarymanagementsystem.repository.AuthorRepository;
import ru.arbiter2008.librarymanagementsystem.service.AuthorService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with ID %d not found", id)));
    }

    @Override
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with ID %d not found", id)));
        authorRepository.delete(author);
    }

    @Override
    public Page<Author> findPaginated(Pageable pageable) {
        var pageSize = pageable.getPageSize();
        var currentPage = pageable.getPageNumber();
        var startItem = currentPage * pageSize;
        List<Author> authorList;

        if (findAllAuthors().size() < startItem) {
            authorList = Collections.emptyList();
        } else {
            var toIndex = Math.min(startItem + pageSize, findAllAuthors().size());
            authorList = findAllAuthors().subList(startItem, toIndex);
        }

        return new PageImpl<>(authorList, PageRequest.of(currentPage, pageSize), findAllAuthors().size());
    }

}
