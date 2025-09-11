package com.logichain.common.dto;

import java.math.BigDecimal;

public class ProductDetailsDTO {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productImage;
    private Long productId;
    private String hsnCode;

    public String getProductCreated() {
        return productCreated;
    }

    public void setProductCreated(String productCreated) {
        this.productCreated = productCreated;
    }

    public String getProductExpiry() {
        return productExpiry;
    }

    public void setProductExpiry(String productExpiry) {
        this.productExpiry = productExpiry;
    }

    private String productCategory;
    private String productCreated;
    private String productExpiry;

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductDetailsDTO(String productName, String productDescription,
            BigDecimal productPrice, String productImage, Long productId, String hsnCode, String productCategory,
            String productCreated, String productExpiry) {
        this.productName = productName;
        this.productId = productId;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = "http://localhost:8080/uploads/" + productImage;
        this.hsnCode = hsnCode;
        this.productCategory = productCategory;
        this.productCreated = productCreated;
        this.productExpiry = productExpiry;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
