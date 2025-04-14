package com.inditex.demo.infrastructure.out.persistance;

import com.inditex.demo.domain.model.Price;
import com.inditex.demo.domain.port.PriceRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public Optional<Price> findPrice(LocalDateTime date, Integer productId, Integer brandId) {
        return Optional.ofNullable(jpaPriceRepository.findPrice(productId, brandId, date, date))
                .map(this::mapToPrice);
    }

    private Price mapToPrice(PriceEntity priceEntity) {
        return new Price(
        priceEntity.getProductId(),
        priceEntity.getBrandId(),
        priceEntity.getPriceList(),
        priceEntity.getStartDate(),
        priceEntity.getEndDate(),
        priceEntity.getPriority(),
        priceEntity.getPrice(),
        priceEntity.getCurr());
    }


}
