package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder,Long> {
}
