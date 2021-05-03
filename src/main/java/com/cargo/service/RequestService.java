package com.cargo.service;

import com.cargo.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findRequests(int status);
    Request postRequest(Request request);
//    List<Request> findAll();
    void updateRequestStatus(Long id, Integer status);
}
