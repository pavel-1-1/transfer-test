package com.example.springdemo.dto;

public class TransferException extends RuntimeException {
    private final Integer operationId;

    public TransferException(String message, int operationId) {
        super(message);
        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }
}