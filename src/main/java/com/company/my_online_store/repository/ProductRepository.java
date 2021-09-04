package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findProductById(UUID id);

    Page<ProductEntity> findAllByOrderByProductId(Pageable pageable);
}
