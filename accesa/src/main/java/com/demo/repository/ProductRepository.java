package com.demo.repository;

import com.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Spring> {
    Product findById(String id);
}
