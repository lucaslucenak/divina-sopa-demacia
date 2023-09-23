package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.OrderRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-request")
public class OrderRequestController {

    @Autowired
    OrderRequestService orderRequestService;

    @PostMapping(value = "/save-order")
    public ResponseEntity<SendMessageResult> requestOrder(@RequestBody String body) {
        return ResponseEntity.ok().body(orderRequestService.requestOrder(body));
    }
}
