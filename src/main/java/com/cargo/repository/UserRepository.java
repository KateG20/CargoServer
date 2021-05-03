package com.cargo.repository;

import com.cargo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE login = ?1 AND password = ?2",
            nativeQuery = true)
    User checkCredentials(String login, String password);

    // TODO какая-то база ключей
    @Query(value = "SELECT * FROM key WHERE value = ?1",
            nativeQuery = true)
    User checkKey(String key);
}
