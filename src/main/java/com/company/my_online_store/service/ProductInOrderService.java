package com.company.my_online_store.service;

import com.company.my_online_store.model.entity.ProductInOrder;
import com.company.my_online_store.model.entity.User;

public interface ProductInOrderService {
    void update(Long id, Integer quantity, User user);

    ProductInOrder findById(Long id, User user);
}
