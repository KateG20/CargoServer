package com.cargo.entity;

import lombok.NonNull;

public class Credentials {
    @NonNull
    String login;
    @NonNull
    String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
