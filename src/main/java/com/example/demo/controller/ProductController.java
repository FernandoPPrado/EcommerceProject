package com.example.demo.controller;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok().body(productService.createProduct(productRequestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Integer id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok().body(productService.updateProduct(id, productRequestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    //ADICIONAR PAGINACAO SE NECESSARIO
    @GetMapping(path = "/getall")
    public ResponseEntity<List<ProductResponseDTO>> getAllUser() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }


}
