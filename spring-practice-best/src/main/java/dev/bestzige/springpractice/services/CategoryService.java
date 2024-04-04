package dev.bestzige.springpractice.services;

import dev.bestzige.springpractice.dtos.CategoryDTO;
import dev.bestzige.springpractice.dtos.CreateCategoryDto;
import dev.bestzige.springpractice.entities.Category;
import dev.bestzige.springpractice.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Category getCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public CategoryDTO createCategory(CreateCategoryDto data) {
        Category category = modelMapper.map(data, Category.class);
        category.setId(null);
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO updateCategory(Integer categoryId, CreateCategoryDto data) {
        Category category = getCategory(categoryId);
        if (category == null) throw notFound(categoryId);
        modelMapper.map(data, category);
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO deleteCategory(Integer categoryId) {
        Category category = getCategory(categoryId);
        if (category == null) throw notFound(categoryId);
        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    /* Error Response */
    public ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Category id not found !!! (" + id + ")"
        );
    }

    public ResponseStatusException conflict(Integer id, Integer bookId) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Category id (" + id + ") already has book id (" + bookId + ")"
        );
    }
}
