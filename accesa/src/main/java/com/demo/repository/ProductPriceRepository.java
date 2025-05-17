package com.demo.repository;

import com.demo.model.Product;
import com.demo.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {
    ProductPrice findTopByProductIdAndStoreIdOrderByDateDesc(String productId, String storeId);
    List<ProductPrice> findAllByProductAndActiveTrue(Product product);
}
