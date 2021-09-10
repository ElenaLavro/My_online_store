package com.company.my_online_store.service;

import com.company.my_online_store.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductEntity findById(Long productId);

    Page<ProductEntity> findAll(Pageable pageable);

    void increaseAmount(Long productId, int amount);

    void decreaseAmount(Long productId, int amount);

    ProductEntity update(ProductEntity product);

    ProductEntity save(ProductEntity product);

    void delete(Long productId);


}
