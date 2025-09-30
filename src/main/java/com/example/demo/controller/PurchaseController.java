package com.example.demo.controller;

import com.example.demo.dto.PurchaseLinkResponseDTO;
import com.example.demo.dto.PurchaseRequestDTO;
import com.example.demo.dto.PurchaseResponseDTO;
import com.example.demo.dto.PurchaseUpdateDTO;
import com.example.demo.model.User;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PurchaseLinkResponseDTO> createNewPurchase(@RequestBody @Valid PurchaseRequestDTO purchaseUpdateDTO) throws AccessDeniedException {
        if (userService.havePermission(purchaseUpdateDTO.userId())) {
            //metodo que retorna o mercado pago
            String string = "TESTE";
            return ResponseEntity.ok().body(new PurchaseLinkResponseDTO(purchaseService.createPurchase(purchaseUpdateDTO.userId(), purchaseUpdateDTO.productId(), purchaseUpdateDTO.purchaseStatus()), string));
        } else throw new AccessDeniedException("USUARIO NAO POSSUI PERMISSÃO");
    }

    @PreAuthorize("isAutenticated()")
    @GetMapping(path = "/me")
    public ResponseEntity<List<PurchaseResponseDTO>> getMyPurchases(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(purchaseService.getPurchaseFromUser(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/getall")
    public ResponseEntity<List<PurchaseResponseDTO>> getAllPurchase() {
        return ResponseEntity.ok().body(purchaseService.getAllPurchase());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<PurchaseResponseDTO> getProductId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(purchaseService.getPurchaseFromId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<PurchaseResponseDTO> updatePurchase(@PathVariable Integer id, @RequestBody @Valid PurchaseUpdateDTO purchaseUpdateDTO) {
        return ResponseEntity.ok().body(purchaseService.updatePurchase(id, purchaseUpdateDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable Integer id) {
        try {
            purchaseService.deletePurchase(id);
            return ResponseEntity.ok().body("PurchaseDeleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
