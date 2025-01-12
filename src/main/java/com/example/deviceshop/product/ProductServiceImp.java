package com.example.deviceshop.product;

import com.example.deviceshop.dto.ProductDto;
import com.example.deviceshop.exception.NotFoundException;
import com.example.deviceshop.model.request.ProductRequest;
import com.example.deviceshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        BigDecimal offer = productRequest.getOfferPrice() != null ? productRequest.getOfferPrice() : BigDecimal.ZERO;
        BigDecimal price = productRequest.getPrice() != null ? productRequest.getPrice() : BigDecimal.ZERO;

        BigDecimal priceDifference = price.subtract(offer);


        // Map ProductRequest to ProductEntity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productRequest.getProductName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPaymentMethod(productRequest.getPaymentMethod());
        productEntity.setVat(productRequest.getVat());
        productEntity.setOfferPrice(productRequest.getOfferPrice());
        productEntity.setTotal(priceDifference);

        // Handle image file upload
        MultipartFile imageFile = productRequest.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "C:/uploads/";
            String imageName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);

            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(imageName);
                imageFile.transferTo(filePath.toFile());
                productEntity.setImage("/uploads/" + imageName);
            } catch (IOException e) {
                throw new IllegalArgumentException("Error saving image to disk: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("No image file provided or the file is empty.");
        }

        productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    public List<ProductDto> searchProduct(String keyWord) {
        if (keyWord == null || keyWord.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<ProductEntity> productEntities = productRepository.findByInformation(keyWord);

        return productEntities.stream()
                .map(productEntity -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setName(productEntity.getProductName());
                    productDto.setDescription(productEntity.getDescription());
                    productDto.setStock(productEntity.getStock());
                    productDto.setPrice(productEntity.getPrice());
                    productDto.setImage(productEntity.getImage());
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductEntity> updateProductInfo(Long id, ProductRequest productRequest) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id is not found", "02-U02-002"));


        productEntity.setProductName(productRequest.getProductName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPaymentMethod(productRequest.getPaymentMethod());
        productEntity.setTotal(productRequest.getTotal());
        productEntity.setVat(productRequest.getVat());
        productEntity.setOfferPrice(productRequest.getOfferPrice());

        productRepository.save(productEntity);
        return Optional.of(productEntity);
    }

    @Override
    public ProductEntity getProductDetails(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not"));
    }

    @Override
    public List<ProductEntity> getAllProduct() {

        return productRepository.findAll();

    }
    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id) .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

}



