package com.example.springdemo.controller;

import com.example.springdemo.service.Service;
import com.example.springdemo.userCard.CardTransfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class postController {

    private final Service service;

    public postController(Service service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody CardTransfer cardTransfer) throws IOException {
        return service.transfer(cardTransfer);
    }

    @PostMapping("/confirmOperation")
    public String validOperation() {
        //service.validTransfer(validTransfer);
        return "Hello Pavel";
    }
}
