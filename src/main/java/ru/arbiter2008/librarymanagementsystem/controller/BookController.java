package ru.arbiter2008.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.arbiter2008.librarymanagementsystem.model.Book;
import ru.arbiter2008.librarymanagementsystem.service.AuthorService;
import ru.arbiter2008.librarymanagementsystem.service.BookService;
import ru.arbiter2008.librarymanagementsystem.service.CategoryService;
import ru.arbiter2008.librarymanagementsystem.service.PublisherService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @RequestMapping({"/", "/books"})
    public String findAllBooks(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);
        var bookPage = bookService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("books", bookPage);

        var totalPages = bookPage.getTotalPages();

        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "list-books";
    }

    @RequestMapping("/search-book")
    public String searchBook(Model model, @Param("keyword") String keyword) {
        model.addAttribute("books", bookService.searchBooks(keyword));
        model.addAttribute("keyword", keyword);

        return "list-books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(Model model, @PathVariable("id") long id) {
        model.addAttribute("book", bookService.findBookById(id));

        return "list-book";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model, Book book) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("publishers", publisherService.findAllPublishers());

        return "add-book";
    }

    @RequestMapping("/add-book")
    public String createBook(Model model, Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookService.createBook(book);
        model.addAttribute("book", bookService.findAllBooks());

        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("book", bookService.findBookById(id));

        return "update-book";
    }

    @RequestMapping("update-book/{id}")
    public String updateBook(Model model, @PathVariable("id") long id, Book book, BindingResult result) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        bookService.updateBook(book);
        model.addAttribute("book", bookService.findAllBooks());

        return "redirect:/books";
    }

    @RequestMapping("remove-book/{id}")
    public String deleteBook(Model model, @PathVariable("id") long id) {
        bookService.deleteBook(id);
        model.addAttribute("book", bookService.findAllBooks());

        return "redirect:/books";
    }

}
