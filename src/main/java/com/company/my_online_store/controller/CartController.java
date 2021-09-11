package com.company.my_online_store.controller;

import com.company.my_online_store.form.ItemForm;
import com.company.my_online_store.model.entity.Cart;
import com.company.my_online_store.model.entity.ProductInOrder;
import com.company.my_online_store.model.entity.User;
import com.company.my_online_store.repository.ProductInOrderRepository;
import com.company.my_online_store.service.CartService;
import com.company.my_online_store.service.ProductInOrderService;
import com.company.my_online_store.service.ProductService;
import com.company.my_online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderService productInOrderService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        try {
            cartService.mergeLocalCart(productInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge cart failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return cartService.getCart(user);
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm itemForm, Principal principal) {
        var productInfo = productService.findById(itemForm.getProductId());
        try {
            mergeCart(Collections.singleton(new ProductInOrder(productInfo, itemForm.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{id}")
    public ProductInOrder updateItem(@PathVariable("id") Long itemId,
                                     @RequestBody Integer quantity,
                                     Principal principal) {
        User user = userService.findByUsername(principal.getName());
        productInOrderService.update(itemId, quantity, user);
        return productInOrderService.findById(itemId, user);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") String itemId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        cartService.delete(itemId, user);
    }

    public ResponseEntity checkout(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }
}
