package com.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "store_prices")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Store store;

    private Double price;
    private String currency;
    private LocalDate date;

    public ProductPrice() {
    }

    public ProductPrice(Product product, Store store, Double price, String currency, LocalDate date) {
        this.product = product;
        this.store = store;
        this.price = price;
        this.currency = currency;
        this.date = date;
    }
}
