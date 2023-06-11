package com.example.springdemo.repository;

import com.example.springdemo.dto.CardTransfer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Confirmation {
    private CardTransfer[] cardTransfers = new CardTransfer[1];
    private String id;
    private final String code = "45";

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CardTransfer getCardTransfers() {
        return cardTransfers[0];
    }

    public void setCardTransfers(CardTransfer[] cardTransfers) {
        this.cardTransfers = cardTransfers;
    }
}
