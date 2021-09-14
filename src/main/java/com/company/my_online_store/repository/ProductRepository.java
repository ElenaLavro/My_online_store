package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    @Query("select p from  ProductEntity p where p.id = :id")
    @Modifying
    ProductEntity findProductById(Long id);

    Page<ProductEntity> findAllByOrderByProductId(Pageable pageable);
}
