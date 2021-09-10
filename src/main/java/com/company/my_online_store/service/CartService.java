package com.company.my_online_store.service;

import com.company.my_online_store.model.entity.Cart;
import com.company.my_online_store.model.entity.ProductInOrder;
import com.company.my_online_store.model.entity.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
