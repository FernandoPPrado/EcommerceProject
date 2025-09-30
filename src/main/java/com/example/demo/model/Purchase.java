package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    private LocalDateTime purchaseDate;

    public Purchase() {
        this.purchaseDate = LocalDateTime.now();
        this.purchaseStatus = PurchaseStatus.PENDING;
    }


    public Purchase(User user, Product product, PurchaseStatus purchaseStatus) {
        this.user = user;
        this.product = product;
        this.purchaseStatus = purchaseStatus;
        this.purchaseDate = LocalDateTime.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
