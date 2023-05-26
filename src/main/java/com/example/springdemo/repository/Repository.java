package com.example.springdemo.repository;

import com.example.springdemo.userCard.Card;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class Repository {
    private final Map<Long, Card> cards = new ConcurrentHashMap<>();

    public Repository() {
        cards.put(1111111111111111L, new Card(1111111111111111L, 111, 11, 23, 12000));
        cards.put(1111111111111122L, new Card(1111111111111122L, 112, 12, 24, 12000));
        cards.put(1111111111111123L, new Card(1111111111111123L, 113, 10, 26, 12000));
        cards.put(1111111111111124L, new Card(1111111111111124L, 114, 6, 23, 12000));
    }

    public Map<Long, Card> getCards() {
        return cards;
    }
}
