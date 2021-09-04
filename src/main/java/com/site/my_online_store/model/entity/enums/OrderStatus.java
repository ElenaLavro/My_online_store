package com.site.my_online_store.model.entity.enums;

public enum OrderStatus implements CodeEnum {
    NEW(0, "New Order"), FINISHED(1, "Finished"), CANCELED(2, "Canceled");

    private int code;
    private String message;

    OrderStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
