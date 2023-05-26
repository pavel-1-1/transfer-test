package com.example.springdemo.userCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Card {
    private long cardNumber;
    private int cvv;
    private int mm;
    private int gg;
    private int amount;
}
