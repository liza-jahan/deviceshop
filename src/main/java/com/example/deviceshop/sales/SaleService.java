package com.example.deviceshop.sales;

import com.example.deviceshop.model.request.SaleRequest;
import com.example.deviceshop.model.request.ProductRequest;

import java.util.List;

public interface SaleService {
    Long saveCashMemo(SaleRequest saleRequest, ProductRequest productRequest);
    List<SaleEntity> getAllInfo();
    public List<SaleEntity> getSalesForProduct(Long productId);

}
