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
    private Double packageQuantity;
    private String packageUnit;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductPrice> prices = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductDiscount> discounts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceAlert> priceAlerts = new ArrayList<>();

    public Product() {
    }

    public Product(String id, String name, String category, String brand, Double packageQuantity, String packageUnit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.packageUnit = packageUnit;
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

    public Double getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Double package_quantity) {
        this.packageQuantity = package_quantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String package_unit) {
        this.packageUnit = package_unit;
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

    public List<PriceAlert> getPriceAlerts() {
        return priceAlerts;
    }

    public void setPriceAlerts(List<PriceAlert> priceAlerts) {
        this.priceAlerts = priceAlerts;
    }

    public void addPriceAlert(PriceAlert priceAlert) {
        this.priceAlerts.add(priceAlert);
        priceAlert.setProduct(this);
    }

    public void removePriceAlert(PriceAlert priceAlert) {
        this.priceAlerts.remove(priceAlert);
    }

    @Override
    public String toString() {
        return "Product{" +
                "package_unit='" + packageUnit + '\'' +
                ", package_quantity=" + packageQuantity +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
