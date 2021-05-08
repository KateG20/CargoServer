package com.cargo.repository;

import com.cargo.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KeyRepository extends JpaRepository<Key, String> {
    @Query(value = "SELECT * FROM key WHERE value = ?1",
            nativeQuery = true)
    Key checkKey(String key);
}
