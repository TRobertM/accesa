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
}
