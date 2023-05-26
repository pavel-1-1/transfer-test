package com.example.springdemo.repository;

import com.example.springdemo.userCard.CardTransfer;

public class Confirmation {
    private CardTransfer[] cardTransfers = new CardTransfer[1];
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardTransfer[] getCardTransfers() {
        return cardTransfers;
    }

    public void setCardTransfers(CardTransfer[] cardTransfers) {
        this.cardTransfers = cardTransfers;
    }
}
