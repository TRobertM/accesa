package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "price_alerts")
public class PriceAlert {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double wantedPrice;

    public PriceAlert() {
    }

    public PriceAlert(Product product, User user, Double wantedPrice) {
        this.product = product;
        this.user = user;
        this.wantedPrice = wantedPrice;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getWantedPrice() {
        return wantedPrice;
    }

    public void setWantedPrice(Double wantedPrice) {
        this.wantedPrice = wantedPrice;
    }
}
