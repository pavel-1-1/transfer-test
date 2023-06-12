package com.example.springdemo.repository;

import com.example.springdemo.userCard.Amount;
import com.example.springdemo.userCard.CardUser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RepositoryUserCard {
    private final Map<String, CardUser> cards = new HashMap<>();

    public RepositoryUserCard() {
        cards.put("1111111111111111", new CardUser("11111111111111111", "111", "11",
                "23", new Amount(12000, "ru")));
        cards.put("1111111111111122", new CardUser("11111111111111122", "112", "12",
                "24", new Amount(12000, "ru")));
        cards.put("1111111111111123", new CardUser("11111111111111123", "113", "10",
                "26", new Amount(12000, "ru")));
        cards.put("1111111111111124", new CardUser("11111111111111124", "114", "6",
                "23", new Amount(12000, "ru")));
    }

    public Map<String, CardUser> getCards() {
        return cards;
    }

    public void transfer(String cardFrom, String cardTo, Amount amount) {
        CardUser cardFroms = cards.get(cardFrom);
        CardUser cardTos = cards.get(cardTo);
        Amount amountFrom = new Amount(cardFroms.getAmount().getValue() - amount.getValue(), amount.getCurrency());
        cardFroms.setAmount(amountFrom);
        cards.put(cardFroms.getCardNumber(), cardFroms);
        Amount amountTo = new Amount(cardTos.getAmount().getValue() + amount.getValue(), amount.getCurrency());
        cardTos.setAmount(amountTo);
        cards.put(cardTos.getCardNumber(), cardTos);
        System.out.println("card from: " + cardFroms.getCardNumber() + ": " + cards.get(cardFroms.getCardNumber()).getAmount().getValue() +
                "\n" + "card to: " + cardTos.getCardNumber() + ": " + cards.get(cardTos.getCardNumber()).getAmount().getValue());
    }
}
