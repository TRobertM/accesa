package com.demo.repository;

import com.demo.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, String> {
    Basket findByUserId(String userId);
}
