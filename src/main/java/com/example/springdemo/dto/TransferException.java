package com.example.springdemo.dto;

public class TransferException extends RuntimeException {
    private final String operationId;

    public TransferException(String message, String operationId) {
        super(message);
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }
}
