package com.example.deviceshop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {

   //  private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime saleDate;
    private Long productId;
}
