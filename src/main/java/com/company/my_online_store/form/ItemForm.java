package com.company.my_online_store.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ItemForm {
    @Min(value = 1)
    private Integer quantity;

    @NotEmpty
    private Long productId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
