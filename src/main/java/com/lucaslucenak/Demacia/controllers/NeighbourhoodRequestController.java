package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.NeighbourhoodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neighbourhood-request")
public class NeighbourhoodRequestController {

    @Autowired
    NeighbourhoodRequestService neighbourhoodRequestService;

    @PostMapping(value = "/save-neighbourhood")
    public ResponseEntity<SendMessageResult> requestNeighbourhood(@RequestBody String body) {
        return ResponseEntity.ok().body(neighbourhoodRequestService.requestNeighbourhood(body));
    }
}
