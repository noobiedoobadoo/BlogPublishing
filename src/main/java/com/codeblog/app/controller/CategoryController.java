package com.codeblog.app.controller;

import com.codeblog.app.payloads.CategoryDto;
import com.codeblog.app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //Post
    @PostMapping("/")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categorySaved = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categorySaved, HttpStatus.CREATED);
    }
    //put
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto categoryUpdated = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
    }
    //get
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer categoryId){
        CategoryDto category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.FOUND);
    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    //get all
    @GetMapping("/")
    public ResponseEntity<?> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.FOUND);
    }
}
