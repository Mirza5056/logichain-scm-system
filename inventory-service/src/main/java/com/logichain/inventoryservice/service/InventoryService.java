package com.logichain.inventoryservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.logichain.common.model.Product;
import com.logichain.inventoryservice.model.InventoryItem;
import com.logichain.inventoryservice.model.Warehouse;
import com.logichain.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {
    @Autowired 
    private InventoryRepository inventoryRepository;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private ProductService productService;
    
    public InventoryItem addInventoryItem(Long productId,Long warehouseId,int quantity) {
        Product product = productService.getProductById(productId);
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        InventoryItem item = new InventoryItem();
        item.setProduct(product);
        item.setWarehouse(warehouse);
        item.setQuantity(quantity);
        item.setLocalDateTime(LocalDateTime.now());
        return inventoryRepository.save(item);
    }

    public InventoryItem updateInventory(Long itemId,int qty) {
        InventoryItem item = inventoryRepository.findById(itemId)
            .orElseThrow(()-> new RuntimeException("Inventory item not found."));
        
        item.setQuantity(qty);
        item.setLocalDateTime(LocalDateTime.now());
        return inventoryRepository.save(item);
    }

    public List<InventoryItem> getInventoryItemByWarehouse(Long warehouseId) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return inventoryRepository.findByWarehouse(warehouse);
    }

    public List<InventoryItem> getInventoryItemByProduct(Long productId) {
        Product product = productService.getProductById(productId);
        return inventoryRepository.findByProductId(product);
    }
}
