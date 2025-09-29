package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseStatus;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUser(User user);

    List<Purchase> findByProduct(Product product);

    List<Purchase> findByPurchaseStatus(PurchaseStatus purchaseStatus);
}
