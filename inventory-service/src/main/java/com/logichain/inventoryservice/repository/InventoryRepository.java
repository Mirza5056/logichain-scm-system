package com.logichain.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logichain.common.model.Product;
import com.logichain.inventoryservice.model.InventoryItem;
import com.logichain.inventoryservice.model.Warehouse;
import java.util.*;
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByWarehouse(Warehouse warehouse);
    List<InventoryItem> findByProductId(Product product);
}
