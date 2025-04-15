package com.inditex.demo.infrastructure.in.rest.controller;

import com.inditex.demo.domain.model.Price;
import com.inditex.demo.domain.service.PriceService;
import com.inditex.demo.infrastructure.in.rest.dto.PriceResponseDto;
import com.inditex.demo.infrastructure.in.rest.exceptions.InvalidParametersException;
import org.openapitools.api.PricesApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController implements PricesApi {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<Object> getPrice(LocalDateTime consultationDate, Integer productId, Integer brandId) {
        if (consultationDate == null || productId <= 0 || brandId <= 0) {
            throw new InvalidParametersException("Invalid parameters");
        }

        Price price = priceService.getApplicablePrice(consultationDate, productId, brandId);

        return ResponseEntity.ok(new PriceResponseDto(price));
    }
}
