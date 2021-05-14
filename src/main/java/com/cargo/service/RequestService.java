package com.cargo.service;

import com.cargo.entity.Request;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RequestService {
    List<Request> findNewRequests(Integer userId);
    List<Request> findCurrentOrArchiveRequests(Integer status, Integer userId);
    Request postRequest(Request request);
//    List<Request> findAll();
    void updateRequestStatus(Long id, Integer status);
    List<Request> getFilteredRequests(Integer status, Integer userId, String from, String to, Long dateFrom,
                                      Long dateTo, Integer minWeight, Integer maxWeight, Integer minPrice,
                                      Integer maxPrice, Integer minDist, Integer maxDist);
//    Request addRequestToUser(Integer userId, Request request);
    void linkRequestToUser(Long requestId, Integer userId);
    void rejectRequest(Long requestId, Integer userId);
}
