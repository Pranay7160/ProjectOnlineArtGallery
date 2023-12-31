package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Arts;

@Repository
public interface ArtsRepository extends JpaRepository<Arts, Long> {
    // You can add custom query methods here if needed
}

