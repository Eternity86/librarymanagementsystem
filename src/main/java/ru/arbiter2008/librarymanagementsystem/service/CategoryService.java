package ru.arbiter2008.librarymanagementsystem.service;

import ru.arbiter2008.librarymanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategories();

    Category findCategoryById(Long id);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);

}
