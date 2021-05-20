package com.cargo.service;

import com.cargo.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findNewRequests(Integer userId);

    List<Request> findCurrentOrArchiveRequests(Integer status, Integer userId);

    Request postRequest(Request request);

    boolean updateRequestStatus(Long id, Integer status, Integer userId);

    List<Request> getFilteredRequests(Integer status, Integer userId, String from, String to, Long dateFrom,
                                      Long dateTo, Integer minWeight, Integer maxWeight, Integer minPrice,
                                      Integer maxPrice, Integer minDist, Integer maxDist);

    void rejectRequest(Long requestId, Integer userId);
}
