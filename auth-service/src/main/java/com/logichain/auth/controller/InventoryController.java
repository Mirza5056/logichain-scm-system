package com.logichain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.logichain.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @PostMapping("/addProductInventory")
    public ResponseEntity<?> addWarehouse(@RequestParam Long productId,@RequestParam Long warehouseId,@RequestParam int qty) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(productId, warehouseId, qty));
    }

    @PostMapping("/updateInventory")
    public ResponseEntity<?> updateInventory(@RequestParam Long itemId,@RequestParam int qty) {
        try
        {
            return ResponseEntity.ok(inventoryService.updateInventory(itemId, qty));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/getInventoryByWarehouseId")
    public ResponseEntity<?> getInventoryByWarehouse(@RequestParam Long id) {
        try
        {
            return ResponseEntity.ok(inventoryService.getInventoryItemByWarehouse(id));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/getInventoryByProductId")
    public ResponseEntity<?> getInventoryByProduct(@RequestParam Long id) {
        try
        {
            return ResponseEntity.ok(inventoryService.getInventoryItemByProduct(id));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }
}
