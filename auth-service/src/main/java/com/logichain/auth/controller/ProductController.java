package com.logichain.auth.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.logichain.common.dto.MessageResponse;
import com.logichain.common.model.Category;
import com.logichain.common.model.Product;
import com.logichain.common.repository.CategoryRepository;
import com.logichain.inventoryservice.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Value("${upload.dir}")
    private String uploadProductImage;

    @PostMapping(value = "/addProduct", consumes = { "multipart/form-data" })
    public ResponseEntity<?> addProduct(@RequestParam("categoryId") Long categoryId,@RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile, @RequestParam("name") String name,
            @RequestParam("hsnCode") String hsnCode, @RequestParam("price") BigDecimal price,
            @RequestParam("productCreated") String productCreated,
            @RequestParam("productExpiry") String productExpiry) throws IOException {
        try {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
            File uploadDir = new File(uploadProductImage);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadProductImage, fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            //String fullPathImage = filePath.toAbsolutePath().toString();
            
            Product product = new Product();
            product.setCategory(category);
            product.setDescription(description);
            product.setImage(fileName);
            product.setName(name);
            product.setHsnCode(hsnCode);
            product.setPrice(price);
            product.setProductCreated(productCreated);
            product.setProductExpiry(productExpiry);
            productService.createProduct(product);
            return ResponseEntity.ok(new MessageResponse(true, "product have been added."));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(new MessageResponse(false, r.getMessage()));
        }
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, product));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product Deleted Successfully.");
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }
}
