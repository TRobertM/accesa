package com.demo.repository;

import com.demo.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {
    ProductPrice findTopByProductIdAndStoreIdOrderByDateDesc(String productId, String storeId);
}
