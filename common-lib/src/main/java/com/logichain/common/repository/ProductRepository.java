package com.logichain.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logichain.common.model.Product;

public interface ProductRepository extends JpaRepository<Product , Long> {
    Optional<Product> findById(Long id);
    boolean existsProductByName(String productName);
}
