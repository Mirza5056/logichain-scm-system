package com.logichain.inventoryservice.dto;

import java.time.LocalDateTime;

import com.logichain.inventoryservice.model.EntityStatus;

public class InventoryItemDetails {
    private Long inventoryId;
    private String productName;
    private String warehouseName;
    private int quantity;
    private String location;
    private String minStock;
    private String maxStock;
    private EntityStatus status;
    private LocalDateTime lastUpdate;
    public InventoryItemDetails(Long inventoryId,String productName, String warehouseName, int quantity, String location,
            String minStock, String maxStock, EntityStatus status, LocalDateTime lastUpdate) {
        this.productName = productName;
        this.inventoryId=inventoryId;
        this.warehouseName = warehouseName;
        this.quantity = quantity;
        this.location = location;
        this.minStock = minStock;
        this.maxStock = maxStock;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getMinStock() {
        return minStock;
    }
    public void setMinStock(String minStock) {
        this.minStock = minStock;
    }
    public String getMaxStock() {
        return maxStock;
    }
    public void setMaxStock(String maxStock) {
        this.maxStock = maxStock;
    }
    public EntityStatus getStatus() {
        return status;
    }
    public void setStatus(EntityStatus status) {
        this.status = status;
    }
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public Long getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }
}
