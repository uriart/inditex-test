package com.inditex.demo.infrastructure.in.rest.dto;

import com.inditex.demo.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Currency;

public record PriceResponseDto(
        Integer productId,
        Integer brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String price) {

    public PriceResponseDto(Price price) {
        this(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.price().toString() + getCurrencySymbol(price.currency())
        );
    }

    private static String getCurrencySymbol(String curr) {
        Currency currency = Currency.getInstance(curr);
        return currency.getSymbol();
    }
}