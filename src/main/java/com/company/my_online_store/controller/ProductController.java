package com.company.my_online_store.controller;

import com.company.my_online_store.model.entity.ProductEntity;
import com.company.my_online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public Page<ProductEntity> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAll(request);
    }

    @GetMapping("/products/{productId}")
    public ProductEntity oneProduct(@PathVariable("productId") Long productId) {
        return productService.findById(productId);
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity createProduct(@Valid @RequestBody ProductEntity product, BindingResult bindingResult) {
        ProductEntity productIdExists = productService.findById(product.getId());
        if (productIdExists != null) {
            bindingResult.rejectValue("productId", "error.product", "This product id was already taken");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") Long productId, @Valid @RequestBody ProductEntity product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getId())) {
            return ResponseEntity.badRequest().body("Product id was not found");
        }
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
