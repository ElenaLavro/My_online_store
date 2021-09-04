package com.example.my_online_store.exceptions;

import com.example.my_online_store.exceptions.auth.InvalidRefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public final class StoreExceptions {
    private StoreExceptions() {
    }

    public static ResponseStatusException authorityNotFound(String value) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User authority" + value + "not defined");
    }

    public static ResponseStatusException userNotFound(String email) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email " + email + "not found");
    }

    public static ResponseStatusException userNotFound(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id " + id + "not found");
    }

    public static ResponseStatusException duplicateEmail(String email) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email " + email + " already taken");
    }

    public static ResponseStatusException duplicateNickname(String nickname) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nickname " + nickname + " already taken");
    }

    public static ResponseStatusException productNotFound(UUID id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found");
    }

    public static ResponseStatusException invalidRefreshToken(InvalidRefreshTokenException cause) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token is invalid! It may have been rotated, invalid or expired naturally", cause);
    }

    public static ResponseStatusException wrongPassword() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
    }
}
