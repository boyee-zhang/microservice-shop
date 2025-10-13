package com.product.ProductService.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void testProductBuilder()
    {
        UUID id = UUID.randomUUID();

        Product product = Product.builder()
                .id(id)
                .sellerId(UUID.randomUUID())
                .name("test product")
                .description("this is a test production")
                .price(new BigDecimal("19.5"))
                .quantity(10)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        assertEquals("this is a test production", product.getDescription());
        assertEquals(10,product.getQuantity());
        assertEquals(BigDecimal.valueOf(19.5),product.getPrice());
    }
}
