package com.logichain.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.logichain.inventoryservice.model.EntityStatus;
import com.logichain.inventoryservice.model.Warehouse;
import com.logichain.inventoryservice.repository.WarehouseRepository;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public Warehouse createWarehouse(Warehouse warehouse) {
        if(warehouse.getCapacity() == null) {
            throw new IllegalArgumentException("warehouse capacity cannot be null.");
        }
        if (warehouseRepository.existsByName(warehouse.getName())) {
            throw new RuntimeException("Warehouse already exists");
        }
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> getWarehouseByStatus(EntityStatus status) {
        List<Warehouse> warehouses = warehouseRepository.findByStatus(status);
        if (warehouses.isEmpty()) {
            throw new RuntimeException("No warehouses found with status " + status);
        }
        return warehouses;
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found."));
    }
}
