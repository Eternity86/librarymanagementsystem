package ru.arbiter2008.librarymanagementsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;

    @Column(name = "serial_name", length = 50, nullable = false)
    private String serialName;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_categories",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_publishers",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<>();

    public Book(String name, String isbn, String serialName, String description) {
        this.name = name;
        this.isbn = isbn;
        this.serialName = serialName;
        this.description = description;
    }
}
