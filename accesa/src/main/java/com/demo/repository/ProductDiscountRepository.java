package com.demo.repository;

import com.demo.model.ProductDiscount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, String> {
    @Query("SELECT pd FROM ProductDiscount pd " +
            "WHERE :currentDate BETWEEN pd.startDate AND pd.endDate " +
            "ORDER BY pd.discount DESC")
    Page<ProductDiscount> findBestActiveDiscounts(LocalDate currentDate, Pageable pageable);

    @Query("SELECT pd FROM ProductDiscount pd " +
            "WHERE pd.startDate IN (:yesterday, :today)")
    Page<ProductDiscount> findNewDiscounts(LocalDate today, LocalDate yesterday, Pageable pageable);
}
