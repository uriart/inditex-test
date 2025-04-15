package com.inditex.demo.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
        SELECT p FROM PriceEntity p
        WHERE p.productId = :productId
          AND p.brandId = :brandId
          AND p.startDate <= :startDate
          AND p.endDate >= :endDate
        ORDER BY p.priority DESC
        LIMIT 1
    """)
    PriceEntity findPrice(
            Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
}
