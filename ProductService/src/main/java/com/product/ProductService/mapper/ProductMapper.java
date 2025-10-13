package com.product.ProductService.mapper;

import com.product.ProductService.DTO.ProductRequestDTO;
import com.product.ProductService.DTO.ProductResponseDTO;
import com.product.ProductService.model.Product;

import java.time.LocalDateTime;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto)
    {
        return Product.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .price(dto.getPrice())
            .quantity(dto.getQuantity())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static ProductResponseDTO toResponseDTO(Product product)
    {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public static void updateEntity(Product product, ProductRequestDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
