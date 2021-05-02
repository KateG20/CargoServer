package com.cargo.service;

import com.cargo.entity.Request;

import java.util.List;

public interface RequestService {
    public List<Request> findRequests(int status);
    public Request postRequest(Request request);
}
