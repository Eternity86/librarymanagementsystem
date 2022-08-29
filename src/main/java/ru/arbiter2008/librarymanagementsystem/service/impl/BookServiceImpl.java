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
import ru.arbiter2008.librarymanagementsystem.model.Book;
import ru.arbiter2008.librarymanagementsystem.repository.BookRepository;
import ru.arbiter2008.librarymanagementsystem.service.BookService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }

        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with ID %d not found", id)));
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with ID %d not found", id)));
        bookRepository.delete(book);
    }

    @Override
    public Page<Book> findPaginated(Pageable pageable) {
        var pageSize = pageable.getPageSize();
        var currentPage = pageable.getPageNumber();
        var startItem = currentPage * pageSize;
        List<Book> bookList;

        if (findAllBooks().size() < startItem) {
            bookList = Collections.emptyList();
        } else {
            var toIndex = Math.min(startItem + pageSize, findAllBooks().size());
            bookList = findAllBooks().subList(startItem, toIndex);
        }

        return new PageImpl<>(bookList, PageRequest.of(currentPage, pageSize), findAllBooks().size());
    }

}
