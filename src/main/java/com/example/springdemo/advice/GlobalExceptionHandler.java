package com.example.springdemo.advice;

import com.example.springdemo.operationId.OperationIdTransfer;
import com.example.springdemo.dto.TransferError;
import com.example.springdemo.dto.TransferException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TransferError> validError(MethodArgumentNotValidException e) {
        TransferError transferError = new TransferError("No value", new OperationIdTransfer().getId());
        return new ResponseEntity<>(transferError, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<TransferError> transferError(TransferException e) {
        TransferError transferError = new TransferError(e.getMessage(), e.getOperationId());
        return new ResponseEntity<>(transferError, HttpStatusCode.valueOf(500));
    }
}
