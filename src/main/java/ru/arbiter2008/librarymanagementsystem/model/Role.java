package ru.arbiter2008.librarymanagementsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL,
//            mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}
