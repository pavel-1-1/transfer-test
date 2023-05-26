package com.example.springdemo;

import java.util.concurrent.atomic.AtomicInteger;

public class OperationId {
    private static final AtomicInteger id = new AtomicInteger(0);

    public OperationId() {
        id.incrementAndGet();
    }

    public int getId() {
        return id.get();
    }
}
