package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.SizeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size-request")
public class SizeRequestController {

    @Autowired
    SizeRequestService sizeRequestService;

    @PostMapping(value = "/save-size")
    public ResponseEntity<SendMessageResult> requestSize(@RequestBody String body) {
        return ResponseEntity.ok().body(sizeRequestService.requestSize(body));
    }
}
