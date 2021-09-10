package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(Long id);

    Page<Order> findAllByOrderStatusOrderByCreateTimeDesc(Boolean status, Pageable pageable);

    Page<Order> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail, Pageable pageable);

    Page<Order> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

    Page<Order> findAllByBuyerUsernameOrderByOrderStatusAscCreateTimeDesc(String username, Pageable pageable);
}
