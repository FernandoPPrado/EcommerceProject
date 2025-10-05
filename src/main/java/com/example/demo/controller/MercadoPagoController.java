package com.example.demo.controller;

import com.example.demo.dto.PaymantRequestDTO;
import com.example.demo.dto.PurchaseLinkResponseDTO;
import com.example.demo.service.MercadoPagoService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/payments")
public class MercadoPagoController {

    private final MercadoPagoService mercadoPagoService;

    public MercadoPagoController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;

    }

    @PreAuthorize("isAuthenticated")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<PurchaseLinkResponseDTO> paymantProdut(@RequestBody @Valid PaymantRequestDTO paymantRequestDTO, @AuthenticationPrincipal UserDetails userDetails) throws MPException, MPApiException {
        return ResponseEntity.ok().body(mercadoPagoService.processPaymant(paymantRequestDTO, userDetails));
    }

    @PostMapping(path = "/webhook")
    public ResponseEntity<Void> updateStatus(@RequestBody Map<String, Object> payload) {
        String externalReference = (String) payload.get("external_reference");
        String status = (String) payload.get("status");

        mercadoPagoService.confirmPayment(externalReference, status);

        return ResponseEntity.ok().build();

    }

}
