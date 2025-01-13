package com.example.deviceshop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {
    List<SaleEntity> findByProductId(Long productId);

}
