package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @Query("select p from ProductEntity p where p.id = :id")
    ProductEntity findProductById(UUID id);


//    Page<ProductEntity> findAllByOrderByProductId(Pageable pageable);
}
