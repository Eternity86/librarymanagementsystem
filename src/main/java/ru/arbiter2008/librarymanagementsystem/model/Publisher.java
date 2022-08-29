package ru.arbiter2008.librarymanagementsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "publishers")
    private Set<Book> books = new HashSet<>();

    public Publisher(String name) {
        this.name = name;
    }

}
