package com.cargo.service;

import com.cargo.entity.Credentials;
import com.cargo.entity.User;
import com.cargo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User checkCredentials(Credentials cred) {
        return userRepository.checkCredentials(cred.getLogin(), cred.getPassword());
    }

    @Override
    public User checkKey(String key) {
        return userRepository.checkKey(key);
    }
}
