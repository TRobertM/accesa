package com.demo.repository;

import com.demo.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Store findByName(String name);
}
