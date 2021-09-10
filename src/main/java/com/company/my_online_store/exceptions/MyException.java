package com.company.my_online_store.exceptions;

import com.company.my_online_store.model.entity.enums.ResultMsg;

public class MyException extends RuntimeException {
    private final Integer code;

    public MyException(ResultMsg resultMsg) {
        super(resultMsg.getMessage());
        this.code = resultMsg.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
