package com.example.demo.service;

import com.example.demo.client.MercadoPagoClient;
import com.example.demo.dto.PaymantRequestDTO;
import com.example.demo.dto.PurchaseLinkResponseDTO;
import com.example.demo.dto.PurchaseResponseDTO;
import com.example.demo.model.Product;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseStatus;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.UserRepository;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {
    private final MercadoPagoClient mercadoPagoClient;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;


    public MercadoPagoService(UserService userService, UserRepository userRepository, ProductService productService, MercadoPagoClient mercadoPagoClient, ProductRepository productRepository, PurchaseService purchaseService, PurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.mercadoPagoClient = mercadoPagoClient;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseLinkResponseDTO processPaymant(PaymantRequestDTO paymantRequestDTO, UserDetails userAuthenticated) throws MPException, MPApiException {

        User user = userRepository.findByUsername(userAuthenticated.getUsername()).orElseThrow(() -> new UsernameNotFoundException("USUAREIO NAO ENCONTRADO"));

        Product product = productRepository.findById(paymantRequestDTO.productId()).orElseThrow(() -> new EntityNotFoundException("PRODUTO NAO ENCONTRADO"));

        Purchase purchase = new Purchase(user, product, PurchaseStatus.PENDING);

        String link = mercadoPagoClient.CreateCheckoutLink(product.getProductName(), paymantRequestDTO.quantity(), product.getProductPrice(), user.getEmail(), user.getUsername(), product.getProductId());

        purchase.setPurchaseStatus(PurchaseStatus.PENDING);
        purchaseRepository.save(purchase);

        return new PurchaseLinkResponseDTO(
                new PurchaseResponseDTO(purchase.getId(),
                        purchase.getUser().getUsername(),
                        purchase.getProduct().getProductName(),
                        purchase.getPurchaseStatus().name(),
                        purchase.getPurchaseDate()), link);


    }

    public void confirmPayment(String purchaseId, String status) {
        Purchase purchase = purchaseRepository.findById(Integer.parseInt(purchaseId)).orElseThrow(() -> new EntityNotFoundException("Purchase nao encontrada"));
        purchase.setPurchaseStatus(PurchaseStatus.valueOf(status.toUpperCase()));

        purchaseRepository.save(purchase);

    }

}
