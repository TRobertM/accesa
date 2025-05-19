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

    private Double wanted_price;

    public PriceAlert() {
    }

    public PriceAlert(Product product, User user, Double wanted_price) {
        this.product = product;
        this.user = user;
        this.wanted_price = wanted_price;
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

    public Double getWanted_price() {
        return wanted_price;
    }

    public void setWanted_price(Double wanted_price) {
        this.wanted_price = wanted_price;
    }
}
