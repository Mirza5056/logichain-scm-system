package com.logichain.inventoryservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.logichain.inventoryservice.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    boolean existsByName(String name);
    Optional<Warehouse> findById(Long id);
}
