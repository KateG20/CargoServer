package com.cargo.service;

import com.cargo.entity.Key;
import com.cargo.entity.User;

public interface UserService {
    User createUser(User user);
    User findUserByLogin(String login);
//    User checkCredentials(Credentials cred);
    Key checkKey(String key);
}
