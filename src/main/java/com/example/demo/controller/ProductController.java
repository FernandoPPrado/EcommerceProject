package com.example.demo.controller;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(path = "/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok().body(productService.createProduct(productRequestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Integer id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok().body(productService.updateProduct(id, productRequestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    //ADICIONAR PAGINACAO SE NECESSARIO
    @PreAuthorize("isAuthenticated")
    @GetMapping(path = "/getall")
    public ResponseEntity<List<ProductResponseDTO>> getAllUser() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }


}
