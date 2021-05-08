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

    @Override
    public List<Request> getFilteredRequests(Integer status, String from, String to, Long dateFrom, Long dateTo,
                                             Integer minWeight, Integer maxWeight, Integer minPrice, Integer maxPrice,
                                             Integer minDist, Integer maxDist) {
        return requestRepository.filterRequests(status, from, to, dateFrom, dateTo, minWeight, maxWeight,
                minPrice, maxPrice, minDist, maxDist);
    }
}
