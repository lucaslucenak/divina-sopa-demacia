package com.lucaslucenak.Demacia.controllers;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.lucaslucenak.Demacia.services.DrinkRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drink-request")
public class DrinkRequestController {

    @Autowired
    DrinkRequestService drinkRequestService;

    @PostMapping(value = "/save-drink")
    public ResponseEntity<SendMessageResult> requestDrink(@RequestBody String body) {
        return ResponseEntity.ok().body(drinkRequestService.requestDrink(body));
    }
}
