package ru.arbiter2008.librarymanagementsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.arbiter2008.librarymanagementsystem.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooks();

    List<Book> searchBooks(String keyword);

    Book findBookById(Long id);

    void createBook(Book book);

    void updateBook(Book book);

    void deleteBook(Long id);

    Page<Book> findPaginated(Pageable pageable);

}
