package com.company.my_online_store.exceptions.auth;

public class InvalidRefreshTokenException extends Exception {
    public InvalidRefreshTokenException() {
        super();
    }

    public InvalidRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
