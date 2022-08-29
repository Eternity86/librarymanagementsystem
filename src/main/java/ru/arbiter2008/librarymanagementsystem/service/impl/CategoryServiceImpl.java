package ru.arbiter2008.librarymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.arbiter2008.librarymanagementsystem.exception.NotFoundException;
import ru.arbiter2008.librarymanagementsystem.model.Category;
import ru.arbiter2008.librarymanagementsystem.repository.CategoryRepository;
import ru.arbiter2008.librarymanagementsystem.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with ID %d not found", id)));
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category with ID %d not found", id)));
        categoryRepository.delete(category);
    }

}
