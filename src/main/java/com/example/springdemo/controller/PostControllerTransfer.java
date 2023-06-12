package com.example.springdemo.controller;

import com.example.springdemo.dto.CardDto;
import com.example.springdemo.dto.ResponsesOperationIdTransfer;
import com.example.springdemo.service.ServiceTransfer;
import com.example.springdemo.verification.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class PostControllerTransfer {

    private final ServiceTransfer service;

    @Autowired
    public PostControllerTransfer(ServiceTransfer service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<ResponsesOperationIdTransfer> transfer(@Valid @RequestBody CardDto cardDto) {
        return new ResponseEntity<>(service.transfer(cardDto), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<ResponsesOperationIdTransfer> validOperation(@RequestBody Verification verification) {
        return new ResponseEntity<>(service.verification(verification), HttpStatusCode.valueOf(200));
    }
}
