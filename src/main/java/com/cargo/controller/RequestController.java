package com.cargo.controller;

import com.cargo.entity.Request;
import com.cargo.entity.User;
import com.cargo.service.RequestService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {
    @Autowired
    RequestService requestService;

    @Autowired
    UserDetailsService userDetailsService;


    @GetMapping("/requests/new/{userId}")
    ResponseEntity<List<Request>> getNewRequests(@PathVariable Integer userId, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetailsService.loadUserByUsername(principal.getUsername());
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userId.equals(user.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        val ordersList = requestService.findNewRequests(userId);
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/requests/current/{userId}")
    ResponseEntity<List<Request>> getCurrentRequests(@PathVariable Integer userId, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetailsService.loadUserByUsername(principal.getUsername());
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userId.equals(user.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        val ordersList = requestService.findCurrentOrArchiveRequests(1, userId);
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/requests/archive/{userId}")
    ResponseEntity<List<Request>> getArchiveRequests(@PathVariable Integer userId, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetailsService.loadUserByUsername(principal.getUsername());
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userId.equals(user.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        val ordersList = requestService.findCurrentOrArchiveRequests(2, userId);
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping("/request/create")
    public ResponseEntity<Request> postRequest(@RequestBody Request request, Authentication authentication) {

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

    @PutMapping("/request/status/{id}/{newStatus}/{userId}")
    public ResponseEntity<Boolean> updateRequestStatus(@PathVariable Long id, @PathVariable Integer newStatus, @PathVariable Integer userId, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetailsService.loadUserByUsername(principal.getUsername());
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userId.equals(user.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if (requestService.updateRequestStatus(id, newStatus, userId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping("/request/reject/{requestId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void rejectRequest(@PathVariable Long requestId, @PathVariable Integer userId, Authentication authentication) {
        requestService.rejectRequest(requestId, userId);
    }

    @GetMapping("/requests/filter")
    ResponseEntity<List<Request>> getFilteredRequests(@RequestParam Integer status, @RequestParam Integer userId,
                                                      @RequestParam String from, @RequestParam String to,
                                                      @RequestParam Long dateFrom, @RequestParam Long dateTo,
                                                      @RequestParam Integer minWeight, @RequestParam Integer maxWeight,
                                                      @RequestParam Integer minPrice, @RequestParam Integer maxPrice,
                                                      @RequestParam Integer minDist, @RequestParam Integer maxDist, Authentication authentication) {
        val requestList = requestService.getFilteredRequests(status, userId, from, to, dateFrom, dateTo, minWeight,
                maxWeight, minPrice, maxPrice, minDist, maxDist);
        return ResponseEntity.ok(requestList);
    }
}
