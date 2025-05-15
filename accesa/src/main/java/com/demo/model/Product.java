package com.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String category;
    private String brand;
    private Double package_quantity;
    private String package_unit;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductPrice> prices = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductDiscount> discounts = new ArrayList<>();

    public Product() {
    }

    public Product(String id, String name, String category, String brand, Double package_quantity, String package_unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.package_quantity = package_quantity;
        this.package_unit = package_unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPackage_quantity() {
        return package_quantity;
    }

    public void setPackage_quantity(Double package_quantity) {
        this.package_quantity = package_quantity;
    }

    public String getPackage_unit() {
        return package_unit;
    }

    public void setPackage_unit(String package_unit) {
        this.package_unit = package_unit;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
    }

    public List<ProductDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<ProductDiscount> discounts) {
        this.discounts = discounts;
    }
}
