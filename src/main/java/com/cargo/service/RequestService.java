package com.cargo.service;

import com.cargo.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findRequests(int status);
    Request postRequest(Request request);
//    List<Request> findAll();
    void updateRequestStatus(Long id, Integer status);
    List<Request> getFilteredRequests(Integer status, String from, String to, Long dateFrom, Long dateTo,
                                      Integer minWeight, Integer maxWeight, Integer minPrice, Integer maxPrice,
                                      Integer minDist, Integer maxDist);
}
