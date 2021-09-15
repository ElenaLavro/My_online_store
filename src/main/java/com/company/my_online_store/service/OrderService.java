package com.company.my_online_store.service;

import com.company.my_online_store.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Page<Order> findByStatus(Integer status, Pageable pageable);

    Page<Order> findByBuyerEmail(String email, Pageable pageable);

    Page<Order> findByBuyerUsername(String username, Pageable pageable);

    Order findById(Long orderId);

    Order finish(Long orderId);

    Order cancel(Long orderId);
}
