package com.demo.repository;

import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, String> {
    @Query("SELECT pd FROM ProductDiscount pd " +
            "WHERE :currentDate BETWEEN pd.startDate AND pd.endDate " +
            "ORDER BY pd.discount DESC")
    Page<ProductDiscount> findBestActiveDiscounts(LocalDate currentDate, Pageable pageable);

    @Query("SELECT pd FROM ProductDiscount pd " +
            "WHERE pd.startDate IN (:yesterday, :today)")
    Page<ProductDiscount> findNewDiscounts(LocalDate today, LocalDate yesterday, Pageable pageable);

    @Query("SELECT pd FROM ProductDiscount pd WHERE " +
            "(:productId IS NULL OR pd.product.id = :productId) AND " +
            "(:storeId IS NULL OR pd.store.id = :storeId) AND " +
            "(:category IS NULL OR pd.product.category = :category) AND " +
            "(:brand IS NULL OR pd.product.brand = :brand) " +
            "ORDER BY pd.product.id, pd.store.id DESC")
    List<ProductDiscount> findPriceHistory(String productId, String storeId, String category, String brand);
}
