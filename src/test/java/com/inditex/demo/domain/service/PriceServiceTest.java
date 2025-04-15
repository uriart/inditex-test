package com.inditex.demo.domain.service;

import com.inditex.demo.domain.exceptions.PriceNotFoundException;
import com.inditex.demo.domain.model.Price;
import com.inditex.demo.domain.ports.PriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepository;

    @InjectMocks
    private PriceService priceService;

    private LocalDateTime date;
    private Integer productId;
    private Integer brandId;

    @BeforeEach
    void setUp() {
        date = LocalDateTime.of(2025, 4, 14, 10, 0, 0, 0);
        productId = 1;
        brandId = 1;
    }

    @Test
    void testGetApplicablePrice_FoundPrice() {
        Price expectedPrice = new Price(1, 1, 1, date, date, 1, new BigDecimal("25.45"), "EUR");
        when(priceRepository.findPrice(date, productId, brandId)).thenReturn(Optional.of(expectedPrice));

        Price actualPrice = priceService.getApplicablePrice(date, productId, brandId);

        assertNotNull(actualPrice);
        assertEquals(expectedPrice, actualPrice);
        verify(priceRepository).findPrice(date, productId, brandId);
    }

    @Test
    void testGetApplicablePrice_PriceNotFound() {
        when(priceRepository.findPrice(date, productId, brandId)).thenReturn(Optional.empty());

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
            priceService.getApplicablePrice(date, productId, brandId);
        });

        assertEquals("No applicable price found", exception.getMessage());
        verify(priceRepository).findPrice(date, productId, brandId);
    }
}