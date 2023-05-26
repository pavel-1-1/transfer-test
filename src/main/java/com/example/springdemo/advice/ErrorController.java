package com.example.springdemo.advice;

import com.example.springdemo.OperationId;
import com.example.springdemo.logger.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validError(MethodArgumentNotValidException e) throws IOException {
        TransferError transferError = new TransferError("No value", new OperationId().getId());
        Logger.getLOG().addLogs(transferError.getMessage() + " id: " + transferError.getOperationId());
        return new ResponseEntity<>(transferError, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<?> transferError(TransferException e) throws IOException {
        TransferError transferError = new TransferError(e.getMessage(), e.getOperationId());
        Logger.getLOG().addLogs(transferError.getMessage() + " id: " + transferError.getOperationId());
        return new ResponseEntity<>(transferError, HttpStatusCode.valueOf(500));
    }
}
