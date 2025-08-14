package com.logichain.suppliers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logichain.suppliers.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
    
}
