package com.company.my_online_store.model.auth.response;

public record AccessTokenResponse(String accessToken, String refreshToken, Long expireIn) {
}
