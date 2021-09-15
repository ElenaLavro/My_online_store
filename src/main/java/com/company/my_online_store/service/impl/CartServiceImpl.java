package com.company.my_online_store.service.impl;

import com.company.my_online_store.model.entity.Cart;
import com.company.my_online_store.model.entity.Order;
import com.company.my_online_store.model.entity.ProductInOrder;
import com.company.my_online_store.model.entity.User;
import com.company.my_online_store.repository.CartRepository;
import com.company.my_online_store.repository.OrderRepository;
import com.company.my_online_store.repository.ProductInOrderRepository;
import com.company.my_online_store.repository.UserRepository;
import com.company.my_online_store.service.CartService;
import com.company.my_online_store.service.ProductService;
import com.company.my_online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
        Cart finalCart = user.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder product;
            if (old.isPresent()) {
                product = old.get();
                product.setCount(productInOrder.getCount());
            } else {
                product = productInOrder;
                product.setCart(finalCart);
                finalCart.getProducts().add(product);
            }
            productInOrderRepository.save(product);
        });
        cartRepository.save(finalCart);
    }

    @Override
    @Transactional
    public void delete(String itemId, User user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });

    }

    @Override
    @Transactional
    public void checkout(User user) {
        Order order = new Order((user));
        orderRepository.save(order);
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrder(order);
            productService.decreaseAmount(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });
    }
}
