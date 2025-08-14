package com.logichain.suppliers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.logichain.suppliers.model.Suppliers;

public interface SupplierRepository extends JpaRepository<Suppliers,Long> {
    boolean existsBySupplierName(String supplierName);
    Optional<Suppliers> findSupplierById(Long id);
}
