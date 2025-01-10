package com.example.deviceshop.repository;

import com.example.deviceshop.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("select p from ProductEntity p where p.name like :key")
    List<ProductEntity> findByInformation(@Param("key") String name );
}
