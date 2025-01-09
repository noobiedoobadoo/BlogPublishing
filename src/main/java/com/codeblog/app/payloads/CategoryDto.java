package com.codeblog.app.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 3, max = 10, message = "title must be >= 3 and <= 10")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Category title must contain only alphabets and spaces")
    private String categoryTitle;
    @NotNull
    private String categoryDesc;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}
