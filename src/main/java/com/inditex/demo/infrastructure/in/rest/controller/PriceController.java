package com.inditex.demo.infrastructure.in.rest.controller;

import com.inditex.demo.domain.model.Price;
import com.inditex.demo.domain.service.PriceService;
import com.inditex.demo.infrastructure.in.rest.dto.PriceResponseDto;
import com.inditex.demo.infrastructure.in.rest.exceptions.InvalidParametersException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime consultationDate,
            @RequestParam Integer productId,
            @RequestParam Integer brandId) {

        if (consultationDate == null || productId <= 0 || brandId <= 0) {
            throw new InvalidParametersException("Invalid parameters");
        }

        Price price = priceService.getApplicablePrice(consultationDate, productId, brandId);

        return ResponseEntity.ok(new PriceResponseDto(price));
    }

}
