package com.logichain.suppliers.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.logichain.suppliers.model.PurchaseOrder;
import com.logichain.suppliers.model.Suppliers;
import com.logichain.suppliers.model.PurchaseOrder.Status;
import com.logichain.suppliers.repository.PurchaseOrderRepository;
import com.logichain.suppliers.repository.SupplierRepository;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        Suppliers suppliers = supplierRepository.findById(purchaseOrder.getSuppliers().getId())
                .orElseThrow(() -> new RuntimeException("Invalid Supplier Id or Does not exists."));
        purchaseOrder.setSuppliers(suppliers);
        purchaseOrder.setCreatedAt(LocalDateTime.now());
        purchaseOrder.setStatus(Status.PENDING);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder getOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid purchase id"));
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) {
        PurchaseOrder purchase = purchaseOrderRepository.findById(purchaseOrder.getSuppliers().getId())
                .orElseThrow(() -> new RuntimeException("Invalid purchase id"));
        purchase.setSuppliers(purchaseOrder.getSuppliers());
        purchase.setCreatedAt(purchaseOrder.getCreatedAt().now());
        purchase.setStatus(purchaseOrder.getStatus());
        return purchaseOrderRepository.save(purchase);
    }

    public List<PurchaseOrder> getAllListOfOrder() {
        return purchaseOrderRepository.findAll();
    }
}
