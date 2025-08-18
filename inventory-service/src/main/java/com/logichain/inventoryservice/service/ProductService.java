package com.logichain.inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.common.model.Product;
import com.logichain.common.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product createProduct(Product product) {
        if(productRepository.existsProductByName(product.getName())) {
            throw new RuntimeException("Product already exits.");
        }
        if(productRepository.existsHsnCodeByName(product.getHsnCode())) {
            throw new RuntimeException("HSN CODE already exits.");
        }
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Product not found."));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product updateProduct(Long id,Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setHsnCode(productDetails.getHsnCode());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
