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
    private Integer discount;
    private Double price;

    public ShoppingListItem() {
    }

    public ShoppingListItem(ShoppingList shoppingList, Product product, Integer quantity, Integer discount, Double price) {
        this.shoppingList = shoppingList;
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingListItem{" +
                "id=" + getId() +
                ", product=" + product +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", price=" + price +
                '}';
    }
}
