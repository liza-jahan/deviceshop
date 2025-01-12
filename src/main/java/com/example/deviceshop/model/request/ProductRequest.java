package com.example.deviceshop.model.request;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String productName;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Stock cannot be null")
    @Positive(message = "Stock must be positive")
    private String stock;

    @NotBlank(message = "Category cannot be blank")
    private String category;
    private MultipartFile image;

    @NotNull(message = "Payment method cannot be null")
    @Size(min = 3, max = 20, message = "Payment method must be between 3 and 20 characters")
    private String paymentMethod;

    @NotNull(message = "Total cannot be null")
    private BigDecimal total;

    private BigDecimal offerPrice;

    @NotNull(message = "VAT cannot be null")
    private BigDecimal vat;

}
