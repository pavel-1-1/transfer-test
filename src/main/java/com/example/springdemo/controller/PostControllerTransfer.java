package com.example.springdemo.controller;

import com.example.springdemo.dto.CardTransfer;
import com.example.springdemo.responses.Res200;
import com.example.springdemo.service.Service;
import com.example.springdemo.verification.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class PostControllerTransfer {

    private final Service service;

    @Autowired
    public PostControllerTransfer(Service service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Res200> transfer(@Valid @RequestBody CardTransfer cardTransfer) {
        return service.transfer(cardTransfer);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<Res200> validOperation(@RequestBody Verification verification) {
        return service.verification(verification);
    }
}
