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
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        String username = authentication.getName();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetailsService.loadUserByUsername(principal.getUsername());
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (!userId.equals(user.getId())) return new ResponseEntity<>(HttpStatus.FORBIDDEN);


//        Boolean match = user.getOrders().stream().anyMatch(order -> order.getId().equals(Long.valueOf(orderId)));
//        if (!match) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        Optional<Order> order = orderService.findById(Long.valueOf(orderId));
//        if (!order.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);


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

    @PutMapping("/request/status/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRequestStatus(@PathVariable Long id, @PathVariable Integer newStatus, Authentication authentication) {
        requestService.updateRequestStatus(id, newStatus);
    }

    @PutMapping("/request/add/{requestId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void linkRequestToUser(@PathVariable Long requestId, @PathVariable Integer userId, Authentication authentication) {
        requestService.linkRequestToUser(requestId, userId);
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
        // если пустая дата фром, то 0
        // если пустая дата ту, то 335619200000
        val requestList = requestService.getFilteredRequests(status, userId, from, to, dateFrom, dateTo, minWeight,
                maxWeight, minPrice, maxPrice, minDist, maxDist);
        return ResponseEntity.ok(requestList);
    }

//    @PutMapping("/request/add/{userId}")
//    public ResponseEntity<Request> addRequestToUser(@PathVariable Integer userId, @RequestBody Request request) {
//        try {
//            Request addedRequest = requestService.addRequestToUser(userId, request);
//            if (addedRequest == null) {
//                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//            return new ResponseEntity<>(addedRequest, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
