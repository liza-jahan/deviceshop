package com.example.deviceshop.sales;


import com.example.deviceshop.model.request.ProductRequest;
import com.example.deviceshop.model.request.SaleRequest;
import com.example.deviceshop.product.ProductEntity;
import com.example.deviceshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleServiceImp implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    public Long saveCashMemo(SaleRequest saleRequest, ProductRequest productRequest) {
        ProductEntity productEntity = productRepository.findById(saleRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found for ID: " + saleRequest.getProductId()));
        BigDecimal price = productRequest.getPrice();
        BigDecimal vat = productRequest.getVat();
        BigDecimal offer = productRequest.getOfferPrice() != null ? productRequest.getOfferPrice() : BigDecimal.ZERO;
        BigDecimal total = price.add(offer).add(vat);


        SaleEntity saleEntity = SaleEntity.builder()
                .productName(productEntity.getProductName())
                .quantity(saleRequest.getQuantity())
                .totalPrice(total.multiply(BigDecimal.valueOf(saleRequest.getQuantity())))
                .saleDate(LocalDateTime.now())
                .build();

        saleRepository.save(saleEntity);
        return saleEntity.getId();
    }

    @Override
    public List<SaleEntity> getAllInfo() {
        return saleRepository.findAll();
    }
}
