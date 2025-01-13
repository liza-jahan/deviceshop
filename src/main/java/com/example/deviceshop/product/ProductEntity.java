package com.example.deviceshop.product;


import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.sales.SaleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Entity
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private BigDecimal price;

    private String description;

    private String stock;


    private String category;

    @Column(name = "photo")
    private String image;

    private String paymentMethod;
    private BigDecimal total;
    private BigDecimal offerPrice;
    private BigDecimal vat;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleEntity> sales;
    @ManyToOne
    private UserEntity userEntity;
}
