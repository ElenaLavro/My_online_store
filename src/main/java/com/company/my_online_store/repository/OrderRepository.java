package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(Long id);

    Page<Order> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);

    Page<Order> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail, Pageable pageable);

    Page<Order> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

    Page<Order> findAllBybuyerNameOrderByOrderStatusAscCreateTimeDesc(String username, Pageable pageable);
}
