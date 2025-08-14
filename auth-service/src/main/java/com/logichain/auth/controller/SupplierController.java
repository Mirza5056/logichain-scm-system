package com.logichain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logichain.suppliers.model.Suppliers;
import com.logichain.suppliers.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    
    @PostMapping("/addSuppliers")
    public ResponseEntity<?> createSupplier(@RequestBody Suppliers suppliers) {
        try
        {
            return ResponseEntity.ok(supplierService.createSupplier(suppliers));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @PostMapping("/updateSuppliers")
    public ResponseEntity<?> updateSupplier(@RequestParam Long id,@RequestBody Suppliers suppliers) {
        try
        {
            return ResponseEntity.ok(supplierService.updateSuppliers(id,suppliers));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/getBySupplierId/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
        try
        {
            return ResponseEntity.ok(supplierService.getSupplierById(id));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/getAllSupplier")
    public ResponseEntity<?> getAllSupplier() {
        try
        {
            return ResponseEntity.ok(supplierService.getAllSuppliersList());
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @DeleteMapping("/deleteSupplier/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try
        {
            supplierService.deleteSuppliers(id);
            return ResponseEntity.ok("Supplier Deleted Successfully.");
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

}
