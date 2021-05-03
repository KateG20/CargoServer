package com.cargo.repository;

import com.cargo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT * FROM request WHERE request.status = %1", nativeQuery = true)
    List<Request> findRequests(int status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Request r SET r.status = %2 WHERE u.id = ?1")
    void updateRequestStatus(Long id, Integer status);
}
