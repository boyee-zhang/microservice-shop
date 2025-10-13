package com.product.ProductService.DTO;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
}