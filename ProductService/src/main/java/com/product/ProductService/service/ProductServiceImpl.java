package com.product.ProductService.service;
import com.product.ProductService.DTO.ProductRequestDTO;
import com.product.ProductService.DTO.ProductResponseDTO;
import com.product.ProductService.mapper.ProductMapper;
import com.product.ProductService.repository.*;
import com.product.ProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = ProductMapper.toEntity(productRequestDTO);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saved = productRepository.save(product);
        return ProductMapper.toResponseDTO(saved);
    }


    @Override
    public ProductResponseDTO getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toResponseDTO(product);
    }

    public List<ProductResponseDTO> getProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO updateProductById(ProductRequestDTO productRequestDTO) {
        UUID id = productRequestDTO.getId();
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null for update");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot update, product not found with id: " + id));

        ProductMapper.updateEntity(product, productRequestDTO);
        Product updated = productRepository.save(product);

        return ProductMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteProductById(UUID id) {
        if(!productRepository.existsById(id))
        {
            throw  new RuntimeException("Cannot delete: Product not found with id: " + id);
        }

        productRepository.deleteById(id);
    }
}
