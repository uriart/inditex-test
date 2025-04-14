package com.inditex.demo.domain.port;

import com.inditex.demo.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findPrice(LocalDateTime date, Integer productId, Integer brandId);
}
