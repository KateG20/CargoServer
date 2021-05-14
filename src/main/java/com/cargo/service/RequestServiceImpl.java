package com.cargo.service;

import com.cargo.entity.Request;
import com.cargo.entity.User;
import com.cargo.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

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
    public void updateRequestStatus(Long id, Integer status) {
        requestRepository.updateRequestStatus(id, status);
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
//    public Request addRequestToUser(Integer userId, Request request) {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        User user = em.find(User.class, userId);
//
//        List<Long> requests = user.getRequests();
//
//        requests.add(request.getId());
//
//        em.persist(user);
//
//        tx.commit();
//
//        em.close();
//
//        return request;
//    }

    @Override
    public void linkRequestToUser(Long requestId, Integer userId) {
        requestRepository.linkRequestToUser(requestId, userId);
    }

    @Override
    public void rejectRequest(Long requestId, Integer userId) {
        requestRepository.rejectRequest(requestId, userId);
    }
}
