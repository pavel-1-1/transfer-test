package com.example.springdemo.controller;

import com.example.springdemo.service.Service;
import com.example.springdemo.userCard.CardTransfer;
import com.example.springdemo.verification.Verification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class postController {

    private final Service service;

    public postController(Service service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody CardTransfer cardTransfer) {
        return service.transfer(cardTransfer);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<?> validOperation(@RequestBody Verification verification) {
        return service.verification(verification);
    }
}
