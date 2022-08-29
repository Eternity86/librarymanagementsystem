package ru.arbiter2008.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.arbiter2008.librarymanagementsystem.model.Category;
import ru.arbiter2008.librarymanagementsystem.service.CategoryService;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @RequestMapping("/categories")
    public String findAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());

        return "category/list-categories";
    }


    @GetMapping("/addCategory")
    public String showCreateForm(Category category) {
        return "category/add-category";
    }

    @RequestMapping("/add-category")
    public String createCategory(Model model, Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/add-category";
        }

        categoryService.createCategory(category);
        model.addAttribute("category", categoryService.findAllCategories());

        return "redirect:/categories";
    }

    @GetMapping("/updateCategory/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("category", categoryService.findCategoryById(id));

        return "category/update-category";
    }

    @RequestMapping("/update-category/{id}")
    public String updateCategory(Model model, @PathVariable("id") Long id, Category category, BindingResult result) {
        if (result.hasErrors()) {
            category.setId(id);
            return "category/update-category";
        }

        categoryService.updateCategory(category);
        model.addAttribute("category", categoryService.findAllCategories());

        return "redirect:/categories";
    }

    @RequestMapping("/remove-category/{id}")
    public String deleteCategory(Model model, @PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        model.addAttribute("category", categoryService.findAllCategories());

        return "redirect:/categories";
    }

}
