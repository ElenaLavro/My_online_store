package com.site.my_online_store.model.entity.enums;

public enum ResultMsg {
    PARAN_ERROR(1, "Parameter Error!"),
    PRODUCT_NOT_EXIST(10, "product does not exist!"),
    PRODUCT_NOT_ENOUGHT(11, "Not enought products in stock!"),
    PRODUCT_STATUS_ERROR(12, "Status is incorrect!"),
    PRODUCT_NOT_IN_CART(14, "Product is not in the cart!"),
    CART_CHECKOUT_SUCCESS(20, "Checkout successfully!"),
    CATEGORY_NOT_FOUND(30, "Checkout does not exist!"),
    ORDER_NOT_FOUND(60, "Order is not exist!"),
    ORDER_STATUS_ERROR(61, "Status is not correct"),
    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUND(50, "User is not found!");

    private Integer code;
    private String message;

    ResultMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
