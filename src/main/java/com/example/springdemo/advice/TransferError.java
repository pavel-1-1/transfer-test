package com.example.springdemo.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransferError {
    private String message;
    private Integer operationId;
}
