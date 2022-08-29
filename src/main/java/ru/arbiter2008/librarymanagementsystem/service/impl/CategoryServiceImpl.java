package ru.arbiter2008.librarymanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import ru.arbiter2008.librarymanagementsystem.model.Category;
import ru.arbiter2008.librarymanagementsystem.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAllCategories() {
        return null;
    }

    @Override
    public Category findCategoryById(Long id) {
        return null;
    }

    @Override
    public void createCategory(Category category) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(Long id) {

    }

}
