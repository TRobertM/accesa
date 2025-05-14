package com.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shopping_list_items")
public class ShoppingListItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ShoppingList shoppingList;

    @ManyToOne
    private Product product;

    private Integer quantity;
}
