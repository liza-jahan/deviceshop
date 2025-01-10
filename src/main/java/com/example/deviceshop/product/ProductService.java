package com.example.deviceshop.product;

import com.example.deviceshop.dto.ProductDto;
import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.model.request.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Long saveProduct(ProductRequest productRequest);

    ProductEntity getProductDetails(Long id);

    Optional<ProductEntity> updateProductInfo(Long id,ProductRequest productRequest);
    List<ProductEntity> getAllProduct();
    List<ProductDto> searchProduct(String keyWord);
}
