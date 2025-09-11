package com.logichain.inventoryservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "city",nullable = false, unique = true)
    private String location;
    
    @Column(name = "manager",nullable = false)
    private String manager;
    @Column(name = "capacity",nullable = false)
    private String capacity;
    @Column(name = "current_stock",nullable = false)
    private String currentStock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityStatus status = EntityStatus.ACTIVE; 

    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getCurrentStock() {
        return currentStock;
    }
    public void setCurrentStock(String currentStock) {
        this.currentStock = currentStock;
    }
    public EntityStatus getStatus() {
        return status;
    }
    public void setStatus(EntityStatus status) {
        this.status = status;
    }
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
