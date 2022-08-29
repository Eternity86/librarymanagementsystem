package ru.arbiter2008.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arbiter2008.librarymanagementsystem.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
