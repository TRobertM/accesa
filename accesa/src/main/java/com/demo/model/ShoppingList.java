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

    public ShoppingList() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getShoppingLists().add(this);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItem> items) {
        this.items = items;
        for (ShoppingListItem item : items) {
            item.setShoppingList(this);
        }
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", store=" + store +
                ", createdAt=" + createdAt +
                ", items=" + items +
                '}';
    }
}
