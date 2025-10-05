package com.example.demo.controller;

import com.example.demo.dto.PaymantRequestDTO;
import com.example.demo.dto.PurchaseLinkResponseDTO;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseStatus;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.service.MercadoPagoService;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
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

    PaymentClient client = new PaymentClient();

    private final PurchaseRepository purchaseRepository;
    private final MercadoPagoService mercadoPagoService;

    public MercadoPagoController(PurchaseRepository purchaseRepository, MercadoPagoService mercadoPagoService) {
        this.purchaseRepository = purchaseRepository;
        this.mercadoPagoService = mercadoPagoService;

    }

    @PreAuthorize("isAuthenticated")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<PurchaseLinkResponseDTO> paymantProdut(@RequestBody @Valid PaymantRequestDTO paymantRequestDTO, @AuthenticationPrincipal UserDetails userDetails) throws MPException, MPApiException {
        return ResponseEntity.ok().body(mercadoPagoService.processPaymant(paymantRequestDTO, userDetails));
    }

    //VALIDAÇAO MERCADOPAGO
    @PostMapping(path = "/webhook")
    public ResponseEntity<Void> updateStatus(@RequestBody Map<String, Object> payload) throws MPException, MPApiException {
        Object idObject = payload.get("id");
        Long id = Long.parseLong(idObject.toString());
        Payment payment = client.get(id);
        Purchase purchase = purchaseRepository.findById(Integer.parseInt(payment.getExternalReference())).orElseThrow();
        PurchaseStatus status = PurchaseStatus.fromMercadoPagoStatus(payment.getStatus());
        purchase.setPurchaseStatus(status);
        purchaseRepository.save(purchase);
        return ResponseEntity.ok().build();

    }

}
