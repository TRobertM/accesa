package com.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

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
    private boolean active;

    public ProductPrice() {
    }

    public ProductPrice(Product product, Store store, Double price, String currency, LocalDate date) {
        this.product = product;
        this.store = store;
        this.price = price;
        this.currency = currency;
        this.date = date;
        this.active = true;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", store=" + store +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                ", isActive=" + active +
                '}';
    }
}
