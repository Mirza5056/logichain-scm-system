package com.logichain.inventoryservice.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.common.dto.ProductDetailsDTO;
import com.logichain.common.model.Product;
import com.logichain.common.repository.CategoryRepository;
import com.logichain.common.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(Product product) {
        if (productRepository.existsProductByName(product.getName())) {
            throw new RuntimeException("Product already exits.");
        }
        if (productRepository.existsHsnCodeByName(product.getHsnCode())) {
            throw new RuntimeException("HSN CODE already exits.");
        }
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public List<ProductDetailsDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDetailsDTO(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImage(),
                        product.getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
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
