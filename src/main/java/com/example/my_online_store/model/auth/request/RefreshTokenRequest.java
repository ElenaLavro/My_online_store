package com.example.my_online_store.model.auth.request;

import com.sun.istack.NotNull;

public record RefreshTokenRequest(@NotNull String refreshToken) {
}
