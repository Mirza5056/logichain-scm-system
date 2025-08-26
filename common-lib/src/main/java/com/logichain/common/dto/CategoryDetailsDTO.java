package com.logichain.common.dto;

public class CategoryDetailsDTO {
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private String categoryName;
    private String categoryImage;

    public CategoryDetailsDTO(String categoryName, String categoryImage, Long categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.categoryImage = "http://localhost:8080/uploads/" + categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
}
