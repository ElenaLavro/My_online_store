package com.site.my_online_store.model.entity.enums;

public enum ProductStatus implements CodeEnum {
    UP(0, "Available"), DOWN(1, "Unavailable");
    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus(Integer code) {
        for (ProductStatus value : ProductStatus.values()) {
            if (value.getCode().equals(code)) return value.getMessage();
        }
        return "";
    }
}
