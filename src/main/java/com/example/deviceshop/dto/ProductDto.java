package com.example.deviceshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;

    private BigDecimal price;

    private String description;

    private String stock;
    private String image;
    private String category;
}
