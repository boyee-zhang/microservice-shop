package com.product.ProductService.service;

import com.product.ProductService.DTO.ProductRequestDTO;
import com.product.ProductService.DTO.ProductResponseDTO;
import com.product.ProductService.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void testCreateProduct()
    {
        ProductRequestDTO request = ProductRequestDTO.builder()
                .name("test product")
                .price(BigDecimal.valueOf(200.0))
                .quantity(10)
                .description("Test description")
                .build();

        ProductResponseDTO saved = productService.createProduct(request);

        assertNotNull(saved.getId());
        assertEquals("test product", saved.getName());
        assertEquals(BigDecimal.valueOf(200.0), saved.getPrice());
    }

    @Test
    void testGetProductById() {
        ProductRequestDTO request = ProductRequestDTO.builder()
                .name("sample")
                .price(BigDecimal.valueOf(99.99))
                .quantity(5)
                .description("Sample product")
                .build();

        ProductResponseDTO saved = productService.createProduct(request);
        ProductResponseDTO found = productService.getProductById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
        assertEquals("sample", found.getName());
    }

    @Test
    void testGetProductsByName() {
        String name = "testName_" + UUID.randomUUID();

        ProductRequestDTO product1 = ProductRequestDTO.builder()
                .name(name)
                .price(BigDecimal.valueOf(10))
                .quantity(1)
                .description("First")
                .build();

        ProductRequestDTO product2 = ProductRequestDTO.builder()
                .name(name)
                .price(BigDecimal.valueOf(20))
                .quantity(2)
                .description("Second")
                .build();

        productService.createProduct(product1);
        productService.createProduct(product2);

        List<ProductResponseDTO> result = productService.getProductsByName(name);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateProductById() {
        ProductRequestDTO request = ProductRequestDTO.builder()
                .name("Old Name")
                .price(BigDecimal.valueOf(50))
                .quantity(3)
                .description("Old desc")
                .build();

        ProductResponseDTO saved = productService.createProduct(request);

        ProductRequestDTO updateRequest = ProductRequestDTO.builder()
                .id(saved.getId())
                .name("New Name")
                .price(BigDecimal.valueOf(75))
                .quantity(5)
                .description("Updated desc")
                .build();

        ProductResponseDTO updated = productService.updateProductById(updateRequest);

        assertEquals("New Name", updated.getName());
        assertEquals(BigDecimal.valueOf(75), updated.getPrice());
        assertEquals("Updated desc", updated.getDescription());
    }

    @Test
    void testDeleteProductById() {
        ProductRequestDTO request = ProductRequestDTO.builder()
                .name("To Be Deleted")
                .price(BigDecimal.valueOf(33.3))
                .quantity(1)
                .description("To delete")
                .build();

        ProductResponseDTO saved = productService.createProduct(request);
        UUID id = saved.getId();

        productService.deleteProductById(id);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(id);
        });

        assertEquals("Product not found with id: " + id, exception.getMessage());
    }

}
