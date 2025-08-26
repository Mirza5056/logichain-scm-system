package com.logichain.common.model;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id",nullable = false)
    private Category category;

    @Column(name = "description",nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "image",nullable = false)
    private String image;
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "hsn_code", nullable = false, unique = true)
    private String hsnCode;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    @Column(name = "product_created", nullable = false)
    private String productCreated;
    @Column(name = "product_expiry", nullable = false)
    private String productExpiry;


    public Long getId() {
        
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHsnCode() {
        return hsnCode;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }
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
}
