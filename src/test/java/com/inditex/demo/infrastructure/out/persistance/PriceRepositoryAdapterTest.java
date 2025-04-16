package com.inditex.demo.infrastructure.out.persistance;

import com.inditex.demo.domain.model.Price;
import com.inditex.demo.infrastructure.out.persistence.JpaPriceRepository;
import com.inditex.demo.infrastructure.out.persistence.PriceEntity;
import com.inditex.demo.infrastructure.out.persistence.PriceRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceRepositoryAdapterTest {

    private JpaPriceRepository jpaPriceRepository;
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @BeforeEach
    void setUp() {
        jpaPriceRepository = mock(JpaPriceRepository.class);
        priceRepositoryAdapter = new PriceRepositoryAdapter(jpaPriceRepository);
    }

    @Test
    void shouldReturnPriceWhenFound() {
        LocalDateTime now = LocalDateTime.now();
        int productId = 35455;
        int brandId = 1;

        PriceEntity entity = new PriceEntity();
        entity.setProductId(productId);
        entity.setBrandId(brandId);
        entity.setPriceList(1);
        entity.setStartDate(now.minusDays(1));
        entity.setEndDate(now.plusDays(1));
        entity.setPriority(1);
        entity.setPrice(new BigDecimal("25.45"));
        entity.setCurr("EUR");

        when(jpaPriceRepository.findPriceByDateProductAndBrand(productId, brandId, now, now)).thenReturn(entity);

        Optional<Price> result = priceRepositoryAdapter.findPrice(now, productId, brandId);

        assertTrue(result.isPresent());
        Price price = result.get();
        assertEquals(productId, price.productId());
        assertEquals(brandId, price.brandId());
        assertEquals(1, price.priceList());
        assertEquals(new BigDecimal("25.45"), price.price());
        assertEquals("EUR", price.currency());
    }

    @Test
    void shouldReturnEmptyWhenPriceNotFound() {
        LocalDateTime now = LocalDateTime.now();
        int productId = 35455;
        int brandId = 1;

        when(jpaPriceRepository.findPriceByDateProductAndBrand(productId, brandId, now, now)).thenReturn(null);
        Optional<Price> result = priceRepositoryAdapter.findPrice(now, productId, brandId);
        assertFalse(result.isPresent());
    }
}
