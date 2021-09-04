package com.example.my_online_store.model.exceptions.auth;

public class InvalidRefreshTokenException extends Exception {
    public InvalidRefreshTokenException() {
        super();
    }

    public InvalidRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
