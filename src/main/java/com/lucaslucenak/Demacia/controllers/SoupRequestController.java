package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.SoupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soup-request")
public class SoupRequestController {

    @Autowired
    SoupRequestService soupRequestService;

    @PostMapping(value = "/save-soup")
    public ResponseEntity<SendMessageResult> requestSoup(@RequestBody String body) {
        return ResponseEntity.ok().body(soupRequestService.requestSoup(body));
    }
}
