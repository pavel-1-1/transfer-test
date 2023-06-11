package com.example.springdemo.service;

import com.example.springdemo.OperationId;
import com.example.springdemo.dto.CardTransfer;
import com.example.springdemo.dto.TransferException;
import com.example.springdemo.logger.Logger;
import com.example.springdemo.repository.Confirmation;
import com.example.springdemo.repository.RepositoryUserCard;
import com.example.springdemo.responses.Res200;
import com.example.springdemo.userCard.Card;
import com.example.springdemo.verification.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class Service {

    private final RepositoryUserCard repository;

    private CardTransfer cardTransfer;

    private final Confirmation confirmation;

    private int operationId;

    @Autowired
    public Service(RepositoryUserCard repository, Confirmation confirmation) {
        this.repository = repository;
        this.confirmation = confirmation;
    }

    public ResponseEntity<Res200> verification(Verification verification) {
        this.operationId = new OperationId().getId();
        try {
            if (confirmation.getCardTransfers() == null) {
                throw new TransferException("hacking attempt!!!", operationId);
            }
        } catch (NullPointerException e) {
            throw new TransferException("hacking attempt!!!", operationId);
        }

        if (Objects.equals(verification.getCode(), confirmation.getCode()) & Objects.equals(verification.getOperationId(), confirmation.getId())) {
            repository.transfer(confirmation.getCardTransfers().getCardFromNumber(), confirmation.getCardTransfers().getCardToNumber()
                    , confirmation.getCardTransfers().getAmount().getValue());
        } else {
            Logger.getLOG().addLogs("card from: " + cardTransfer.getCardFromNumber()
                    + " cardTo: " + cardTransfer.getCardToNumber()
                    + " amount: " + cardTransfer.getAmount().getValue() + " no commission " + "false");
            confirmation.setCardTransfers(null);
            confirmation.setId(null);
            throw new TransferException("Code error", operationId);
        }
        Logger.getLOG().addLogs("card from: " + confirmation.getCardTransfers().getCardFromNumber()
                + " cardTo: " + confirmation.getCardTransfers().getCardToNumber()
                + " amount: " + confirmation.getCardTransfers().getAmount().getValue() + " no commission " + "true");
        confirmation.setCardTransfers(null);
        confirmation.setId(null);
        return new ResponseEntity<>(new Res200(String.valueOf(operationId)), HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<Res200> transfer(CardTransfer cardTransfer) {
        this.cardTransfer = cardTransfer;
        this.operationId = new OperationId().getId();
        Card cardFrom = parseCardTransfer(cardTransfer);
        Long cardToNumber = Long.parseLong(cardTransfer.getCardToNumber(), 10);
        confirmation.setId(String.valueOf(operationId));
        if (repository.getCards().containsKey(cardFrom.getCardNumber()) & repository.getCards().containsKey(cardToNumber)
                & repository.getCards().get(cardFrom.getCardNumber()) != repository.getCards().get(cardToNumber)) {
            Card cardValid = repository.getCards().get(cardFrom.getCardNumber());
            if (cardFrom.getCvv() == cardValid.getCvv() & cardFrom.getMm() == cardValid.getMm() &
                    cardFrom.getGg() == cardValid.getGg() & (!(cardFrom.getAmount() < 1) & cardFrom.getAmount() <= cardValid.getAmount())) {
                confirmation.setCardTransfers(new CardTransfer[]{cardTransfer});
            } else {
                Logger.getLOG().addLogs("card from: " + cardTransfer.getCardFromNumber()
                        + " cardTo: " + cardTransfer.getCardToNumber()
                        + " amount: " + cardTransfer.getAmount().getValue() + " no commission " + "false");
                throw new TransferException("No card valid", operationId);
            }
        } else {
            Logger.getLOG().addLogs("card from: " + cardTransfer.getCardFromNumber()
                    + " cardTo: " + cardTransfer.getCardToNumber()
                    + " amount: " + cardTransfer.getAmount().getValue() + " no commission " + "false");
            throw new TransferException("No card", operationId);
        }
        return new ResponseEntity<>(new Res200(Integer.toString(operationId)), HttpStatusCode.valueOf(200));
    }

    private Card parseCardTransfer(CardTransfer transfer) {
        int[] mmGG = Arrays.stream(transfer.getCardFromValidTill().split("/")).mapToInt(Integer::parseInt).toArray();
        int mm = mmGG[0];
        int gg = mmGG[1];
        return new Card(Long.parseLong(transfer.getCardFromNumber()), Integer.parseInt(transfer.getCardFromCVV()), mm, gg, transfer.getAmount().getValue());
    }
}
