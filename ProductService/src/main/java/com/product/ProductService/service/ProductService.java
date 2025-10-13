package com.product.ProductService.service;
import com.product.ProductService.DTO.ProductRequestDTO;
import com.product.ProductService.DTO.ProductResponseDTO;
import com.product.ProductService.model.Product;
import java.util.*;
public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProductById(UUID id);

    List<ProductResponseDTO> getProductsByName(String name);

    void deleteProductById(UUID id);

    ProductResponseDTO updateProductById(ProductRequestDTO productRequestDTO);
}
