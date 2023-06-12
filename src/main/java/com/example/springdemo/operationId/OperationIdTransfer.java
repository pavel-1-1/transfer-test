package com.example.springdemo.operationId;

import java.util.concurrent.atomic.AtomicInteger;

public class OperationIdTransfer {
    private static final AtomicInteger id = new AtomicInteger(0);

    public OperationIdTransfer() {
        id.incrementAndGet();
    }

    public String getId() {
        return String.valueOf(id.get());
    }
}
