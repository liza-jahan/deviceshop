package com.example.deviceshop.product;

import com.example.deviceshop.dto.ProductDto;
import com.example.deviceshop.exception.NotFoundException;
import com.example.deviceshop.model.request.ProductRequest;
import com.example.deviceshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        // Create a new ProductEntity and map basic details
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setStock(productRequest.getStock());
        productEntity.setDescription(productRequest.getDescription());

        // image file upload
        MultipartFile imageFile = productRequest.getImage(); // Ensure productRequest.getImage() returns a MultipartFile
        if (imageFile != null && !imageFile.isEmpty()) {
            // upload directory and file name
            String uploadDir = "C:/uploads/";
            String imageName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename(); // Add timestamp to avoid duplicates
            Path uploadPath = Paths.get(uploadDir);

            try {
                // Create directories if they don't exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path filePath = uploadPath.resolve(imageName);
                imageFile.transferTo(filePath.toFile());

                // Set the relative file path in the entity
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
        List<ProductEntity> productEntities;
        if (keyWord != null && !keyWord.isEmpty()) {
            productEntities = this.productRepository.findByInformation(keyWord); // No need for "%"
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
                    productDto.setImage(productEntity.getImage());
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
    @Override
    public ProductEntity getProductDetails(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not"));
    }

    @Override
    public List<ProductEntity> getAllProduct() {

        return productRepository.findAll();

    }

}



