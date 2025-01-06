package com.example.deviceshop.product;

import com.example.deviceshop.dto.ProductDto;
import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.exception.NotFoundException;
import com.example.deviceshop.model.request.ProductRequest;
import com.example.deviceshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public Long saveProduct(ProductRequest productRequest) {
        ProductEntity productEntity=new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());
         productRepository.save(productEntity);

        return productEntity.getId();
    }

    @Override
    public List<ProductDto> searchProduct(String keyWord) {
        List<ProductEntity> productEntities;
        if (keyWord != null && !keyWord.isEmpty()) {
            productEntities = this.productRepository.findByTitleContaining(keyWord); // No need for "%"
        } else {
            return Collections.emptyList();
        }

        return productEntities.stream()
                .map(productEntity -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setName(productEntity.getName());
                    productDto.setDescription(productEntity.getDescription());
                    productDto.setStock(productEntity.getStock());
                    productDto.setPrice(productEntity.getPrice());
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductEntity> updateProductInfo(Long id, ProductRequest productRequest) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Id is not found", "02-U02-002"));
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());
        productRepository.save(productEntity);


        return Optional.of(productEntity);


    }

}
