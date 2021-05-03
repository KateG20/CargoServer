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

    @GetMapping("/requests/new")
    ResponseEntity<List<Request>> getNewRequests() {
        val ordersList = requestService.findRequests(0);
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/requests/current")
    ResponseEntity<List<Request>> getCurrentRequests() {
        val ordersList = requestService.findRequests(1);
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/requests/archive")
    ResponseEntity<List<Request>> getArchiveRequests() {
        val ordersList = requestService.findRequests(2);
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

    @PutMapping("/request/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRequestStatus(@RequestBody Request request, @PathVariable Integer status) {
        requestService.updateRequestStatus(request.getId(), status);
    }
}
