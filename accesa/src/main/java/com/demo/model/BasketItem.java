package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "basket_items")
public class BasketItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Basket basket;

    @ManyToOne
    private Product product;

    private Integer quantity;
}
