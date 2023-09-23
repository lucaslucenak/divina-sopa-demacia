package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.ClientAccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-account-request")
public class ClientAccountRequestController {

    @Autowired
    ClientAccountRequestService clientAccountRequestService;

    @PostMapping(value = "/save-clientAccount")
    public ResponseEntity<SendMessageResult> requestClientAccount(@RequestBody String body) {
        return ResponseEntity.ok().body(clientAccountRequestService.requestClientAccount(body));
    }
}
