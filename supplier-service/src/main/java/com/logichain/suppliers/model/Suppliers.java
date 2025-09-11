package com.logichain.suppliers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "suppliers")
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Please enter a supplier name")
    @Column(name = "supplier_name")
    private String supplierName;
    @NotNull(message = "Please enter a supplier email")
    @Column(name = "supplier_email")
    private String contactEmail;
    @NotNull(message = "Please enter a supplier phone number")
    @Column(name = "supplier_phone_number")
    private Integer phoneNumber;
    @Column(name = "supplier_rating")
    private Float rating;

    @Column(name = "contact_person",nullable = false)
    private String contactPerson;
    @Column(name = "products_category")
    private String productsCategory;
    @Column(name = "address",nullable = false)
    private String address;

    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getProductsCategory() {
        return productsCategory;
    }
    public void setProductsCategory(String productsCategory) {
        this.productsCategory = productsCategory;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
}
