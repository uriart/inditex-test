package com.inditex.demo.domain.service;

import com.inditex.demo.domain.exceptions.PriceNotFoundException;
import com.inditex.demo.domain.model.Price;
import com.inditex.demo.domain.ports.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {
    private final PriceRepositoryPort priceRepository;

    public PriceService(PriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getApplicablePrice(LocalDateTime date, Integer productId, Integer brandId) {
        return priceRepository.findPrice(date, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("No applicable price found"));
    }
}
