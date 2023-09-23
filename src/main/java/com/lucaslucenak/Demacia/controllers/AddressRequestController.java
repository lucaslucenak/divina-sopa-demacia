package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.AddressRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address-request")
public class AddressRequestController {

    @Autowired
    AddressRequestService addressRequestService;

    @PostMapping(value = "/save-address")
    public ResponseEntity<SendMessageResult> requestAddress(@RequestBody String body) {
        return ResponseEntity.ok().body(addressRequestService.requestAddress(body));
    }
}
