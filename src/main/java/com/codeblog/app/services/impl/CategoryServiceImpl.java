package com.codeblog.app.services.impl;

import com.codeblog.app.entity.Category;
import com.codeblog.app.exceptions.ResourceNotFoundException;
import com.codeblog.app.payloads.CategoryDto;
import com.codeblog.app.repository.CategoryRepo;
import com.codeblog.app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category categorySaved = categoryRepo.save(dtoToCategory(categoryDto));
        return categoryToDto(categorySaved);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDesc());
        Category categorySaved = categoryRepo.save(category);
        return categoryToDto(categorySaved);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            categoryDtos.add(categoryToDto(category));
        });
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        categoryRepo.deleteById(categoryId);
    }

    private CategoryDto categoryToDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
    private Category dtoToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }
}
