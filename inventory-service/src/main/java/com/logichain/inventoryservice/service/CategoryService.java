package com.logichain.inventoryservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.common.dto.CategoryDetailsDTO;
import com.logichain.common.dto.CategoryDetailsDTOById;
import com.logichain.common.model.Category;
import com.logichain.common.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDetailsDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> new CategoryDetailsDTO(category.getName(), category.getImage(), category.getId()))
                .collect(Collectors.toList());
    }

    public List<CategoryDetailsDTOById> getCategoryById(Long id) {
        List<Category> categories = categoryRepository.findCategoryById(id);
        if (categories == null || categories.isEmpty()) {
            throw new RuntimeException("Invalid category id");
        }
        return categories.stream()
                .flatMap(
                        category -> category.getProducts().stream()
                                .map(prod -> new CategoryDetailsDTOById(
                                        prod.getName(), prod.getDescription(), prod.getPrice(), prod.getImage(),
                                        prod.getId())))
                .collect(Collectors.toList());
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        category.setName(categoryDetails.getName());
        category.setImage(categoryDetails.getImage());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}
