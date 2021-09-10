package com.company.my_online_store.model.entity.enums;

public enum ResultMsg {
    PARAM_ERROR(400, "Parameter Error!"),
    PRODUCT_NOT_EXIST(404, "product does not exist!"),
    PRODUCT_NOT_ENOUGHT(406, "Not enough products in stock!"),
    PRODUCT_STATUS_ERROR(404, "Product status is incorrect!"),
    PRODUCT_NOT_IN_CART(404, "Product is not in the cart!"),
    CART_CHECKOUT_SUCCESS(200, "Checkout successfully!"),
    CATEGORY_NOT_FOUND(404, "Checkout does not exist!"),
    ORDER_NOT_FOUND(404, "Order is not exist!"),
    ORDER_STATUS_ERROR(404, "Order status is not correct"),
    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUND(404, "User is not found!");

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
