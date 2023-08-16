package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    // You can add custom query methods here if needed
}
