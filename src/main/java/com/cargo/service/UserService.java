package com.cargo.service;

import com.cargo.entity.Credentials;
import com.cargo.entity.Key;
import com.cargo.entity.Request;
import com.cargo.entity.User;

public interface UserService {
    User createUser(User user);
    User checkCredentials(Credentials cred);
    Key checkKey(Key key);
}
