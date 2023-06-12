package com.example.springdemo.userCard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardUser {
    private String cardNumber;
    private String cvv;
    private String mm;
    private String gg;
    private Amount amount;
}
