package com.product.ProductService.repository;

import com.product.ProductService.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFindById() {
        Product product = Product.builder()
                .name("Test Product")
                .price(BigDecimal.valueOf(99.99))
                .quantity(5)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product saved = productRepository.save(product);
        assertNotNull(saved.getId());

        var found = productRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Test Product", found.get().getName());
    }

    @Test
    void testFindByNameContainingIgnoreCase() {
        Product product1 = Product.builder()
                .name("Super Camera")
                .price(BigDecimal.valueOf(199.99))
                .quantity(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product2 = Product.builder()
                .name("super Drone")
                .price(BigDecimal.valueOf(299.99))
                .quantity(3)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> results = productRepository.findByNameContainingIgnoreCase("super");

        assertEquals(2, results.size());
    }
}
