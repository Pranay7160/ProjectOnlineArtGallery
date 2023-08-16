package com.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.ArtDetails;

@Repository
public interface ArtDetailsRepository extends JpaRepository<ArtDetails, Long> {
    // You can add custom query methods here if needed
}
