package com.example.springdemo.repository;

import com.example.springdemo.userCard.Card;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RepositoryUserCard {
    private final Map<Long, Card> cards = new ConcurrentHashMap<>();

    public RepositoryUserCard() {
        cards.put(1111111111111111L, new Card(1111111111111111L, 111, 11, 23, 12000));
        cards.put(1111111111111122L, new Card(1111111111111122L, 112, 12, 24, 12000));
        cards.put(1111111111111123L, new Card(1111111111111123L, 113, 10, 26, 12000));
        cards.put(1111111111111124L, new Card(1111111111111124L, 114, 6, 23, 12000));
    }

    public Map<Long, Card> getCards() {
        return cards;
    }

    public void transfer(String cardFrom, String cardTo, int amount) {
        Card cardFroms = cards.get(Long.parseLong(cardFrom, 10));
        Card cardTos = cards.get(Long.parseLong(cardTo, 10));
        cardFroms.setAmount(cardFroms.getAmount() - amount);
        cardTos.setAmount(cardTos.getAmount() + amount);
        System.out.println("card from: " + cards.get(cardFroms.getCardNumber()).getCardNumber() + ": " + cards.get(cardFroms.getCardNumber()).getAmount() + "\n" +
                "card to: " + cards.get(cardTos.getCardNumber()).getCardNumber() + ": " + cards.get(cardTos.getCardNumber()).getAmount());
    }
}
