package com.cargo.service;

import com.cargo.entity.Request;
import com.cargo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> findRequests(int status) {
        return requestRepository.findRequests(status);
    }

    @Override
    public Request postRequest(Request request) {
        return requestRepository.save(request);
    }

//    @Override
//    public List<Request> findAll() {
//        return requestRepository.findAll();
//    }

    @Override
    public void updateRequestStatus(Long id, Integer status) {
        requestRepository.updateRequestStatus(id, status);
    }
}
