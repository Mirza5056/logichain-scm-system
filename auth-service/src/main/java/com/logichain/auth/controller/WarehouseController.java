package com.logichain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logichain.inventoryservice.model.Warehouse;
import com.logichain.inventoryservice.service.WarehouseService;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/addWarehouse")
    public ResponseEntity<?> addWarehouse(@RequestBody Warehouse warehouse) {
        try {
            return ResponseEntity.ok(warehouseService.createWarehouse(warehouse));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/getAllWarehouse")
    public ResponseEntity<?> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouse());
    }

    @GetMapping("/getWarehouseById/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(warehouseService.getWarehouseById(id));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }
}
