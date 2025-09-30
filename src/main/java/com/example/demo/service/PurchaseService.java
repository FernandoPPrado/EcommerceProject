package com.example.demo.service;

import com.example.demo.dto.PurchaseResponseDTO;
import com.example.demo.dto.PurchaseUpdateDTO;
import com.example.demo.model.Product;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseStatus;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public PurchaseService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public PurchaseResponseDTO createPurchase(Integer idUser, Integer idProduct, PurchaseStatus purchaseStatus) {

        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("USUARIO NAO ENCONTRADO"));
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new EntityNotFoundException("PRODUTO NAO ENCONTRADO"));

        Purchase purchase = purchaseRepository.save(new Purchase(user, product, purchaseStatus));
        return new PurchaseResponseDTO(purchase.getId(), purchase.getUser().getUsername(), purchase.getProduct().getProductName(), purchase.getPurchaseStatus().name(), purchase.getPurchaseDate());

    }

    public boolean deletePurchase(Integer id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PURCHASE NOT FOUND"));

        try {
            purchaseRepository.delete(purchase);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public PurchaseResponseDTO updatePurchase(Integer id, PurchaseUpdateDTO purchase, User user, Product product) {
        Purchase purchaseTemp = purchaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PURCHASE NOT FOUND"));
        purchaseTemp.setUser(user);
        purchaseTemp.setProduct(product);
        purchaseTemp.setPurchaseStatus(purchase.purchaseStatus());
        purchaseTemp.setPurchaseDate(purchase.purchaseDate());

        Purchase purchaseUpdated = purchaseRepository.save(purchaseTemp);

        return new PurchaseResponseDTO(purchaseUpdated.getId(), purchaseUpdated.getUser().getUsername(),
                purchaseUpdated.getProduct().getProductName(), purchaseUpdated.getPurchaseStatus().name(), purchaseUpdated.getPurchaseDate());
    }


    public PurchaseResponseDTO changePurchaseStatus(Integer id, PurchaseStatus purchaseStatus) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PURCHASE NOT FOUND"));
        purchase.setPurchaseStatus(purchaseStatus);
        purchaseRepository.save(purchase);
        return new PurchaseResponseDTO(purchase.getId(), purchase.getUser().getUsername(),
                purchase.getProduct().getProductName(), purchase.getPurchaseStatus().name(), purchase.getPurchaseDate());
    }

    public List<PurchaseResponseDTO> getAllPurchase() {
        return purchaseRepository.findAll().stream()
                .map(purchase ->
                        new PurchaseResponseDTO(purchase.getId(), purchase.getUser().getUsername(),
                                purchase.getProduct().getProductName(), purchase.getPurchaseStatus().name(), purchase.getPurchaseDate())).toList();
    }

    public List<PurchaseResponseDTO> getPurchaseFromUser(User user) {
        return purchaseRepository.findByUser(user).stream().map(purchase -> new PurchaseResponseDTO(purchase.getId(), purchase.getUser().getUsername(),
                purchase.getProduct().getProductName(), purchase.getPurchaseStatus().name(), purchase.getPurchaseDate())).toList();
    }

    public List<PurchaseResponseDTO> getPurchaseFromStatus(PurchaseStatus purchaseStatus) {
        return purchaseRepository.findByPurchaseStatus(purchaseStatus).stream().map(purchase -> new PurchaseResponseDTO(purchase.getId(), purchase.getUser().getUsername(),
                purchase.getProduct().getProductName(), purchase.getPurchaseStatus().name(), purchase.getPurchaseDate())).toList();
    }

    public List<PurchaseResponseDTO> getPurchaseFromProduct(Product product) {
        return purchaseRepository.findByProduct(product).stream().map(purchase -> new PurchaseResponseDTO(purchase.getId(), purchase.getUser().getUsername(),
                purchase.getProduct().getProductName(), purchase.getPurchaseStatus().name(), purchase.getPurchaseDate())).toList();
    }

}
