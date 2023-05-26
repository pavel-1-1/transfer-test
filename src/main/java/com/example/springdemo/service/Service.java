package com.example.springdemo.service;

import com.example.springdemo.OperationId;
import com.example.springdemo.advice.TransferException;
import com.example.springdemo.repository.Confirmation;
import com.example.springdemo.repository.Repository;
import com.example.springdemo.responses.Res200;
import com.example.springdemo.userCard.Card;
import com.example.springdemo.userCard.CardTransfer;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;


public class Service {

    private final Repository repository;

    @Resource(name = "confirmation")
    private Confirmation confirmation;

    public Service(Repository repository, Confirmation confirmation) {
        this.repository = repository;
        this.confirmation = confirmation;
    }

    public ResponseEntity<?> transfer(CardTransfer cardTransfer) {
        Card cardFrom = parseCardTransfer(cardTransfer);
        OperationId operationId = new OperationId();
        if (repository.getCards().containsKey(cardFrom.getCardNumber())) {
            Card cardValid = repository.getCards().get(cardFrom.getCardNumber());
            if (cardFrom.getCvv() == cardValid.getCvv() & cardFrom.getMm() == cardValid.getMm() &
                    cardFrom.getGg() == cardValid.getGg() & (!(cardFrom.getAmount() < 1) & cardFrom.getAmount() <= cardValid.getAmount())) {
                confirmation.setCardTransfers(new CardTransfer[]{cardTransfer});
                confirmation.setId(operationId.getId());
            } else {
                throw new TransferException("No card valid", operationId.getId());
            }
        } else {
            throw new TransferException("No card", operationId.getId());
        }
        return new ResponseEntity<>(new Res200(Integer.toString(operationId.getId())), HttpStatusCode.valueOf(200));
    }

    private Card parseCardTransfer(CardTransfer transfer) {
        int[] mmGG = Arrays.stream(transfer.getCardFromValidTill().split("/")).mapToInt(Integer::parseInt).toArray();
        int mm = mmGG[0];
        int gg = mmGG[1];
        return new Card(Long.parseLong(transfer.getCardFromNumber()), Integer.parseInt(transfer.getCardFromCVV()), mm, gg, transfer.getAmount().getValue());
    }
}
