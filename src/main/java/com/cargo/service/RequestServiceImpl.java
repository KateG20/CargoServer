package com.cargo.service;

import com.cargo.entity.Request;
import com.cargo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> findNew() {
        return requestRepository.findAll();
    }
}
