package com.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "store_discounts")
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Store store;

    private Integer discount;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProductDiscount() {
    }

    public ProductDiscount(Product product, Store store, Integer discount, LocalDate startDate, LocalDate endDate) {
        this.product = product;
        this.store = store;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
