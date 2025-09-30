package com.example.demo.controller;

import com.example.demo.dto.PurchaseLinkResponseDTO;
import com.example.demo.dto.PurchaseRequestDTO;
import com.example.demo.dto.PurchaseResponseDTO;
import com.example.demo.dto.PurchaseUpdateDTO;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;
import com.example.demo.repository.PurchaseRepository;
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


}
