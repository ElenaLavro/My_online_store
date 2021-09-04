package com.example.my_online_store.model.auth.request;

import com.fasterxml.jackson.annotation.JsonAlias;

public record SighInRequest(@JsonAlias({"username", "email"})
                            String login,
                            String password) {
}
