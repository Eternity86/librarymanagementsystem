package ru.arbiter2008.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.arbiter2008.librarymanagementsystem.model.Author;
import ru.arbiter2008.librarymanagementsystem.service.AuthorService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping("/authors")
    public String findAllAuthors(Model model, @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {

        var currentPage = page.orElse(1);
        var pageSize = size.orElse(5);
        var bookPage = authorService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("authors", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            var pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "author/list-authors";
    }

    @RequestMapping("/author/{id}")
    public String findAuthorById(Model model, @PathVariable("id") Long id) {

        model.addAttribute("author", authorService.findAuthorById(id));

        return "author/list-author";
    }

    @GetMapping("/addAuthor")
    public String showCreateForm(Author author) {
        return "author/add-author";
    }

    @RequestMapping("/add-author")
    public String createAuthor(Model model, Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author/add-author";
        }

        authorService.createAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());

        return "redirect:/authors";
    }

    @GetMapping("/updateAuthor/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") Long id) {

        model.addAttribute("author", authorService.findAuthorById(id));

        return "author/update-author";
    }

    @RequestMapping("/update-author/{id}")
    public String updateAuthor(Model model, @PathVariable("id") Long id, Author author, BindingResult result) {
        if (result.hasErrors()) {
            author.setId(id);
            return "author/update-author";
        }

        authorService.updateAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());

        return "redirect:/authors";
    }

    @RequestMapping("/remove-author/{id}")
    public String deleteAuthor(Model model, @PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        model.addAttribute("author", authorService.findAllAuthors());

        return "redirect:/authors";
    }

}
