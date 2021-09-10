package com.company.my_online_store.service.impl;

import com.company.my_online_store.exceptions.MyException;
import com.company.my_online_store.model.entity.Order;
import com.company.my_online_store.model.entity.ProductEntity;
import com.company.my_online_store.model.entity.ProductInOrder;
import com.company.my_online_store.model.entity.enums.OrderStatus;
import com.company.my_online_store.model.entity.enums.ResultMsg;
import com.company.my_online_store.repository.OrderRepository;
import com.company.my_online_store.repository.ProductInOrderRepository;
import com.company.my_online_store.repository.ProductRepository;
import com.company.my_online_store.repository.UserRepository;
import com.company.my_online_store.service.OrderService;
import com.company.my_online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
    }

    @Override
    public Page<Order> findByStatus(Boolean status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
    }

    @Override
    public Page<Order> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }

    @Override
    public Page<Order> findByBuyerUsername(String username, Pageable pageable) {
        return orderRepository.findAllByBuyerUsernameOrderByOrderStatusAscCreateTimeDesc(username, pageable);
    }

    @Override
    public Order findById(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            throw new MyException(ResultMsg.ORDER_NOT_FOUND);
        }
        return order;
    }

    @Override
    @Transactional
    public Order finish(Long orderId) {
        Order order = findById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            throw new MyException(ResultMsg.ORDER_STATUS_ERROR);
        }
        order.setOrderStatus(OrderStatus.FINISHED.getCode());
        orderRepository.save(order);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public Order cancel(Long orderId) {
        Order order = findById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            throw new MyException(ResultMsg.ORDER_STATUS_ERROR);
        }
        order.setOrderStatus(OrderStatus.CANCELED.getCode());
        orderRepository.save(order);
        Iterable<ProductInOrder> products = order.getProducts();
        for (ProductInOrder productInOrder : products) {
            ProductEntity productInfo = productRepository.findProductById(productInOrder.getProductId());
            if (productInfo != null) {
                productService.increaseAmount(productInOrder.getProductId(), productInOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);
    }
}
