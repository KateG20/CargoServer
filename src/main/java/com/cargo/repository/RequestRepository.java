package com.cargo.repository;

import com.cargo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT * FROM request WHERE request.status = ?1", nativeQuery = true)
    List<Request> findRequests(Integer status);

    /*
SELECT * FROM request WHERE request.status = 0 AND
(CASE WHEN 'any' != 'any' THEN UPPER(request.source) = UPPER('казань') ELSE TRUE END) AND
(CASE WHEN 'any' != 'any' THEN UPPER(request.destination) = UPPER('казань') ELSE TRUE END) AND
            request.date BETWEEN 0 AND 335619200000 AND
            request.weight BETWEEN 0 AND 100000 AND request.price BETWEEN 0 AND 1000000 AND
            request.distance BETWEEN 0 AND 100000;
     */

    @Query(value = "SELECT * FROM request WHERE request.status = ?1 AND " +
            "(CASE WHEN ?2 != 'any' THEN UPPER(request.source) = UPPER(?2) ELSE TRUE END) AND" +
            "(CASE WHEN ?3 != 'any' THEN UPPER(request.destination) = UPPER(?3) ELSE TRUE END) AND " +
            "request.date BETWEEN ?4 AND ?5 AND request.weight BETWEEN ?6 AND ?7 AND request.price " +
            "BETWEEN ?8 AND ?9 AND request.distance BETWEEN ?10 AND ?11",
            nativeQuery = true)
    List<Request> filterRequests(Integer status, String from, String to, Long dateFrom, Long dateTo,
                                 Integer minWeight, Integer maxWeight, Integer minPrice, Integer maxPrice,
                                 Integer minDist, Integer maxDist);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Request r SET r.status = ?2 WHERE r.id = ?1")
    void updateRequestStatus(Long id, Integer status);
}
