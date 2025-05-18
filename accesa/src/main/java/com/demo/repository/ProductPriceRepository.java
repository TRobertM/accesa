package com.demo.repository;

import com.demo.model.Product;
import com.demo.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {
    ProductPrice findTopByProductIdAndStoreIdOrderByDateDesc(String productId, String storeId);
    List<ProductPrice> findAllByProductAndActiveTrue(Product product);

    @Query("SELECT pp FROM ProductPrice pp WHERE " +
            "(:productId IS NULL OR pp.product.id = :productId) AND " +
            "(:storeId IS NULL OR pp.store.id = :storeId) AND " +
            "(:category IS NULL OR pp.product.category = :category) AND " +
            "(:brand IS NULL OR pp.product.brand = :brand) " +
            "ORDER BY pp.product.id, pp.store.id, pp.date DESC")
    List<ProductPrice> findPriceHistory(String productId, String storeId, String category, String brand);
}
