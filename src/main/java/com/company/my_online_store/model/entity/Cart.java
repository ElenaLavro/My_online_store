package com.company.my_online_store.model.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cart")
    private Set<ProductInOrder> products = new HashSet<>();

    public Cart() {
    }

    public Cart(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", products=" + products +
                '}';
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInOrder> products) {
        this.products = products;
    }
}
