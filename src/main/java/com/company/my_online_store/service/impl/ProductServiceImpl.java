package com.company.my_online_store.service.impl;

import com.company.my_online_store.exceptions.MyException;
import com.company.my_online_store.model.entity.ProductEntity;
import com.company.my_online_store.model.entity.enums.ResultMsg;
import com.company.my_online_store.repository.ProductRepository;
import com.company.my_online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductEntity findById(Long productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAllByOrderById(pageable);
    }

    @Override
    @Transactional
    public void increaseAmount(Long productId, int amount) {
        ProductEntity product = findById(productId);
        if (product == null) throw new MyException(ResultMsg.PRODUCT_NOT_EXIST);
        int updateAmount = product.getProductAmount() + amount;
        product.setProductAmount(updateAmount);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void decreaseAmount(Long productId, int amount) {
        ProductEntity product = findById(productId);
        if (product == null) throw new MyException(ResultMsg.PRODUCT_NOT_EXIST);
        int updateAmount = product.getProductAmount() - amount;
        if (updateAmount <= 0) throw new MyException(ResultMsg.PRODUCT_NOT_ENOUGHT);
        product.setProductAmount(updateAmount);
        productRepository.save(product);
    }

    @Override
    public ProductEntity update(ProductEntity product) {
        if (!product.getAvailability()) throw new MyException(ResultMsg.PRODUCT_STATUS_ERROR);
        return productRepository.save(product);
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        return update(product);
    }

    @Override
    public void delete(Long productId) {
        ProductEntity product = findById(productId);
        if (product == null) throw new MyException(ResultMsg.PRODUCT_NOT_EXIST);
        productRepository.delete(product);
    }
}
