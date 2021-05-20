package com.cargo.service;

import com.cargo.entity.Authority;
import com.cargo.entity.Key;
import com.cargo.entity.User;
import com.cargo.repository.AuthorityRepository;
import com.cargo.repository.KeyRepository;
import com.cargo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private KeyRepository keyRepository;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        authorityRepository.save(new Authority(createdUser.getLogin(), "ROLE_USER"));
        return createdUser;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Key checkKey(String key) {
        return keyRepository.checkKey(key);
    }
}
