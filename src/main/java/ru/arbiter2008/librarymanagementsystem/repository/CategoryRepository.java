package ru.arbiter2008.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arbiter2008.librarymanagementsystem.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
