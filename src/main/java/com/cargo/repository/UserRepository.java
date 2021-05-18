package com.cargo.repository;

import com.cargo.entity.Key;
import com.cargo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query(value = "SELECT * FROM user_table WHERE login = ?1 AND password = ?2",
//            nativeQuery = true)
//    User checkCredentials(String login, String password);

    @Query
    User findByLogin(String login);
}
