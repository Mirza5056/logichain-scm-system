package com.logichain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logichain.suppliers.model.PurchaseOrder;
import com.logichain.suppliers.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/purchaseorder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    
    @PostMapping("/addPurchaseOrder")
    public ResponseEntity<?> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        try
        {
            return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(purchaseOrder));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    } 

    @PostMapping("/updatePurchaseOrder/{id}")
    public ResponseEntity<?> updatePurchaseOrdered(@PathVariable Long id,@RequestBody PurchaseOrder purchaseOrder) {
        try
        {
            return ResponseEntity.ok(purchaseOrderService.updatePurchaseOrder(id,purchaseOrder));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    } 

    @GetMapping("/getPurchaseOrderById/{id}")
    public ResponseEntity<?> getPurchaseOrderById(@PathVariable Long id) {
        try
        {
            return ResponseEntity.ok(purchaseOrderService.getOrderById(id));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    } 

    @GetMapping("/getListOfPurchaseOrder") 
    public ResponseEntity<?> getAllPurchaseOrder() {
        return ResponseEntity.ok(purchaseOrderService.getAllListOfOrder());
    }
}
