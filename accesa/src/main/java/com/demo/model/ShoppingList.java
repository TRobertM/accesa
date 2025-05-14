package com.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shopping_lists")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToOne
    private User user;

    @ManyToOne
    private Store store;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "shoppingList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShoppingListItem> items;
}
