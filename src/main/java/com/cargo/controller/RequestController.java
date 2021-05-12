package com.cargo.controller;

import com.cargo.entity.Request;
import com.cargo.service.RequestService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {
    @Autowired
    RequestService requestService;

    @GetMapping("/requests/new/{userId}")
    ResponseEntity<List<Request>> getNewRequests(Integer userId) {
        val ordersList = requestService.findNewRequests(userId);
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/requests/current/{userId}")
    ResponseEntity<List<Request>> getCurrentRequests(Integer userId) {
        val ordersList = requestService.findRequests(1, userId);
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/requests/archive/{userId}")
    ResponseEntity<List<Request>> getArchiveRequests(Integer userId) {
        val ordersList = requestService.findRequests(2, userId);
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping("/request/create")
    public ResponseEntity<Request> postRequest(@RequestBody Request request) {
        try {
            Request createdRequest = requestService.postRequest(request);
            if (createdRequest == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(createdRequest, HttpStatus.CREATED); // 201
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/request/status/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRequestStatus(@PathVariable Long id, @PathVariable Integer newStatus) {
        requestService.updateRequestStatus(id, newStatus);
    }

    @PutMapping("/request/add/{requestId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void linkRequestToUser(@PathVariable Long requestId, @PathVariable Integer userId) {
        requestService.linkRequestToUser(requestId, userId);
    }

    @PutMapping("/request/reject/{requestId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void rejectRequest(Long requestId, Integer userId) {
        requestService.rejectRequest(requestId, userId);
    }

    @GetMapping("/requests/filter")
    ResponseEntity<List<Request>> getFilteredRequests(@RequestParam Integer status, @RequestParam String from,
                                                      @RequestParam String to, @RequestParam Long dateFrom,
                                                      @RequestParam Long dateTo, @RequestParam Integer minWeight,
                                                      @RequestParam Integer maxWeight, @RequestParam Integer minPrice,
                                                      @RequestParam Integer maxPrice, @RequestParam Integer minDist,
                                                      @RequestParam Integer maxDist) {
//        if (from.equals("any")) from = "request.source";
//        if (to.equals("any")) to = "request.destination";
        // если пустая дата фром, то 0
        // если пустая дата ту, то 335619200000
        val ordersList = requestService.getFilteredRequests(status, from, to, dateFrom, dateTo, minWeight,
                maxWeight, minPrice, maxPrice, minDist, maxDist);
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping("/request/add/{userId}")
    public ResponseEntity<Request> addRequestToUser(@PathVariable Integer userId, @RequestBody Request request) {
        try {
            Request addedRequest = requestService.addRequestToUser(userId, request);
            if (addedRequest == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(addedRequest, HttpStatus.CREATED); // 201
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
