package com.cargo.service;

import com.cargo.entity.Credentials;
import com.cargo.entity.Request;
import com.cargo.entity.User;

public interface UserService {
    public User createUser(User user);
    public User checkCredentials(Credentials cred);
    public User checkKey(String key);
}
