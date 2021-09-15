package com.company.my_online_store.service.impl;

import com.company.my_online_store.model.entity.ProductInOrder;
import com.company.my_online_store.model.entity.User;
import com.company.my_online_store.repository.ProductInOrderRepository;
import com.company.my_online_store.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service("productInOrderService")
public class ProductInOrderServiceImpl implements ProductInOrderService {
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(Long id, Integer quantity, User user) {
        var productInOrder = user.getCart().getProducts().stream().filter(e -> id.equals(e.getProductId())).findFirst();
        productInOrder.ifPresent(p -> {
            p.setCount(quantity);
            productInOrderRepository.save(p);
        });
    }

    @Override
    public ProductInOrder findById(Long id, User user) {
        var po = user.getCart().getProducts().stream().filter(e -> id.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> reference = new AtomicReference<>();
        po.ifPresent(reference::set);
        return reference.get();
    }
}
