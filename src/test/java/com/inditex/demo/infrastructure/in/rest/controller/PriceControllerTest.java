package com.inditex.demo.infrastructure.in.rest.controller;

import com.inditex.demo.domain.model.Price;
import com.inditex.demo.domain.service.PriceService;
import com.inditex.demo.infrastructure.in.rest.dto.PriceResponseDto;
import com.inditex.demo.infrastructure.in.rest.exceptions.InvalidParametersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    private PriceService priceService;
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        priceService = mock(PriceService.class);
        priceController = new PriceController(priceService);
    }

    @Test
    void shouldReturnPriceResponseDtoWhenValidRequest() {
        LocalDateTime date = LocalDateTime.now();
        int productId = 35455;
        int brandId = 1;

        Price price = new Price(
                productId,
                brandId,
                1,
                date.minusDays(1),
                date.plusDays(1),
                1,
                new BigDecimal("25.45"),
                "EUR"
        );

        when(priceService.getApplicablePrice(date, productId, brandId)).thenReturn(price);

        ResponseEntity<Object> response = priceController.getPrice(date, productId, brandId);

        assertEquals(200, response.getStatusCodeValue());
        assertInstanceOf(PriceResponseDto.class, response.getBody());
        PriceResponseDto dto = (PriceResponseDto) response.getBody();
        assertEquals(productId, dto.productId());
        assertEquals(brandId, dto.brandId());
        assertEquals("25.45€", dto.price());
    }

    @Test
    void shouldThrowExceptionWhenInvalidParams() {
        InvalidParametersException ex = assertThrows(InvalidParametersException.class, () ->
                priceController.getPrice(LocalDateTime.now(), 0, 1)
        );
        assertEquals("Invalid parameters", ex.getMessage());

        ex = assertThrows(InvalidParametersException.class, () ->
                priceController.getPrice(LocalDateTime.now(), 1, 0)
        );
        assertEquals("Invalid parameters", ex.getMessage());

        ex = assertThrows(InvalidParametersException.class, () ->
                priceController.getPrice(null, 1, 1)
        );
        assertEquals("Invalid parameters", ex.getMessage());
    }
}
