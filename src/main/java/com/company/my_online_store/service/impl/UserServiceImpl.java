package com.company.my_online_store.service.impl;

import com.company.my_online_store.exceptions.MyException;
import com.company.my_online_store.model.entity.Cart;
import com.company.my_online_store.model.entity.User;
import com.company.my_online_store.model.entity.enums.ResultMsg;
import com.company.my_online_store.repository.CartRepository;
import com.company.my_online_store.repository.UserRepository;
import com.company.my_online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByNickname(username);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);
            Cart savedCart = cartRepository.save(new Cart(savedUser));
            savedUser.setCart(savedCart);
            return userRepository.save(savedUser);
        } catch (Exception e) {
            throw new MyException(ResultMsg.VALID_ERROR);
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setNickname(user.getNickname());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        return userRepository.save(oldUser);
    }
}
