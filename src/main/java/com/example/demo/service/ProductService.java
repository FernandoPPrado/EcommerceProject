package com.example.demo.service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRepository.save(new Product(productRequestDTO.productName(), productRequestDTO.productDescription(), productRequestDTO.productPrice(), productRequestDTO.stockQuantity()));
        return new ProductResponseDTO(product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductPrice(), product.getStockQuantity());

    }

    public ProductResponseDTO deleteProduct(Integer id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        productRepository.delete(product);
        return new ProductResponseDTO(product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductPrice(), product.getStockQuantity());
    }


    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll().stream().map(e -> new ProductResponseDTO(e.getProductId(), e.getProductName(), e.getProductDescription(), e.getProductPrice(), e.getStockQuantity())).toList();
    }


    public ProductResponseDTO getProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PRODUTO NAO ENCONTRADO"));
        return new ProductResponseDTO(product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductPrice(), product.getStockQuantity());
    }


    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO productRequestDTO) {

        Product product = productRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("PRODUTO NAO LOCALIZADO"));

        product.setProductName(productRequestDTO.productName());
        product.setProductDescription(productRequestDTO.productDescription());
        product.setProductPrice(productRequestDTO.productPrice());
        product.setStockQuantity(productRequestDTO.stockQuantity());

        return new ProductResponseDTO(product.getProductId(), product.getProductName(), product.getProductDescription(), product.getProductPrice(), product.getStockQuantity());

    }


}
