package com.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BasketItem> basketItems = new ArrayList<>();

    public Basket() {
    }

    public String getId() {
        return id;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public void addItem(BasketItem item) {
        basketItems.add(item);
        item.setBasket(this);
    }

    public void removeItem(BasketItem item) {
        basketItems.remove(item);
        item.setBasket(null);
    }

}
