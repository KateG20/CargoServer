package com.cargo.repository;

import com.cargo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT * FROM request WHERE request.status = 0 AND " +
            "(NOT EXISTS (SELECT TRUE FROM request_rejected_by WHERE " +
            "request_rejected_by.request_id = request.id AND ?1 = ANY(request_rejected_by.rejected)))",
            nativeQuery = true)
    List<Request> findNewRequests(Integer userId);

    @Query(value = "SELECT * FROM request WHERE request.status = ?1 AND request.user_id = ?2", nativeQuery = true)
    List<Request> findCurrentOrArchiveRequests(Integer status, Integer userId);

    @Query(value = "SELECT * FROM request_rejected_by WHERE request_id = ?1",
            nativeQuery = true)
    List<Request> findRejectedRequests(Long id);

    @Query(value = "SELECT * FROM request WHERE request.status = ?1 AND " +
            "(NOT EXISTS (SELECT TRUE FROM request_rejected_by WHERE " +
            "request_rejected_by.request_id = request.id AND ?12 = ANY(request_rejected_by.rejected))) AND " +
            "(CASE WHEN ?2 != 'any' THEN UPPER(request.source) = UPPER(?2) ELSE TRUE END) AND" +
            "(CASE WHEN ?3 != 'any' THEN UPPER(request.destination) = UPPER(?3) ELSE TRUE END) AND " +
            "request.date BETWEEN ?4 AND ?5 AND request.weight BETWEEN ?6 AND ?7 AND request.price " +
            "BETWEEN ?8 AND ?9 AND request.distance BETWEEN ?10 AND ?11",
            nativeQuery = true)
    List<Request> filterNewRequests(Integer status, String from, String to, Long dateFrom, Long dateTo,
                                    Integer minWeight, Integer maxWeight, Integer minPrice, Integer maxPrice,
                                    Integer minDist, Integer maxDist, Integer userId);

    @Query(value = "SELECT * FROM request WHERE request.status = ?1 AND request.user_id = ?12 AND " +
            "(CASE WHEN ?2 != 'any' THEN UPPER(request.source) = UPPER(?2) ELSE TRUE END) AND" +
            "(CASE WHEN ?3 != 'any' THEN UPPER(request.destination) = UPPER(?3) ELSE TRUE END) AND " +
            "request.date BETWEEN ?4 AND ?5 AND request.weight BETWEEN ?6 AND ?7 AND request.price " +
            "BETWEEN ?8 AND ?9 AND request.distance BETWEEN ?10 AND ?11",
            nativeQuery = true)
    List<Request> filterCurrentAndArchiveRequests(Integer status, String from, String to, Long dateFrom, Long dateTo,
                                                  Integer minWeight, Integer maxWeight, Integer minPrice, Integer maxPrice,
                                                  Integer minDist, Integer maxDist, Integer userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE request SET status = ?2 WHERE id = ?1", nativeQuery = true)
    void updateRequestStatus(Long id, Integer status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE request SET user_id = ?2 WHERE id = ?1 AND user_id = null", nativeQuery = true)
    Integer linkRequestToUser(Long requestId, Integer userId);

//    @Modifying
//    @Transactional // разделить жту штуку на две
//    @Query(value = "INSERT INTO request_rejected_by (request_id, rejected) VALUES (1, ARRAY[2]) " +
//            "ON CONFLICT (request_id) " + //--WHERE request_id > 0
//            "DO UPDATE SET rejected = array_append(request_rejected_by.rejected, 2) " +
//            "WHERE request_rejected_by.request_id = 1",
//            nativeQuery = true)
//    void rejectRequest(Long requestId, Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO request_rejected_by (request_id, rejected) VALUES (?1, ARRAY[?2]) ",
            nativeQuery = true)
    void addRejectedRequest(Long requestId, Integer userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE request_rejected_by " +
            "SET rejected = array_append(request_rejected_by.rejected, ?2) " +
            "WHERE request_rejected_by.request_id = ?1",
            nativeQuery = true)
    void addRejectingUser(Long requestId, Integer userId);
}
