package com.cargo.controller;

import com.cargo.entity.Request;
import com.cargo.service.RequestService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {
    @Autowired
    RequestService requestService;

    @GetMapping("requests/new")
    ResponseEntity<List<Request>> getNewRequests() {
        val ordersList = requestService.findNew();
        return ResponseEntity.ok(ordersList);
    }

}
