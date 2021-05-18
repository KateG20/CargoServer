package com.cargo.service;

import com.cargo.entity.Request;
import com.cargo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> findCurrentOrArchiveRequests(Integer status, Integer userId) {
        return requestRepository.findCurrentOrArchiveRequests(status, userId);
    }

    @Override
    public List<Request> findNewRequests(Integer userId) {
        return requestRepository.findNewRequests(userId);
    }

    @Override
    public Request postRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    @Transactional
    public boolean updateRequestStatus(Long id, Integer status, Integer userId) {
        if (status == 1) {
            // Если вернуло 0, значит ни одна строчка не изменилась => юзер
            // уже был занят
            if (requestRepository.linkRequestToUser(id, userId) == 0) {
                return false;
            }
            // Если привязали юзера, меняем статус
            requestRepository.updateRequestStatus(id, 1);
        } else if (status == 2) {
            requestRepository.updateRequestStatus(id, 2);
        }
        return true;
    }

    @Override
    public List<Request> getFilteredRequests(Integer status, Integer userId, String from, String to, Long dateFrom,
                                             Long dateTo, Integer minWeight, Integer maxWeight, Integer minPrice,
                                             Integer maxPrice, Integer minDist, Integer maxDist) {
        if (status == 0)
            return requestRepository.filterNewRequests(status, from, to, dateFrom, dateTo, minWeight, maxWeight,
                    minPrice, maxPrice, minDist, maxDist, userId);
        else
            return requestRepository.filterCurrentAndArchiveRequests(status, from, to, dateFrom, dateTo, minWeight,
                    maxWeight, minPrice, maxPrice, minDist, maxDist, userId);
    }


//    @Override
//    public boolean linkRequestToUser(Long requestId, Integer userId) {
//        requestRepository.linkRequestToUser(requestId, userId);
//    }

    @Override
    public void rejectRequest(Long requestId, Integer userId) {
        if (requestRepository.findRejectedRequests(requestId).size() == 0) {
            requestRepository.addRejectedRequest(requestId, userId);
        }
        requestRepository.addRejectingUser(requestId, userId);
    }
}
