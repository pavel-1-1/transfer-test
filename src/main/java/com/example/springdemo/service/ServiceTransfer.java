package com.example.springdemo.service;

import com.example.springdemo.dto.CardDto;
import com.example.springdemo.dto.ResponsesOperationIdTransfer;
import com.example.springdemo.dto.TransferException;
import com.example.springdemo.logger.Logger;
import com.example.springdemo.operationId.OperationIdTransfer;
import com.example.springdemo.repository.RepositoryUserCard;
import com.example.springdemo.userCard.CardUser;
import com.example.springdemo.verification.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Objects;
import java.util.Random;

@Component
@SessionScope
public class ServiceTransfer {

    private final RepositoryUserCard repository;

    private CardDto cardDto;

    private String verificationCode;
    private String operationId;

    @Autowired
    public ServiceTransfer(RepositoryUserCard repository) {
        this.repository = repository;
    }

    public ResponsesOperationIdTransfer verification(Verification verification) {
        try {
            if (cardDto == null) {
                throw new TransferException("hacking attempt !!!", operationId);
            }
        } catch (NullPointerException e) {
            throw new TransferException("hacking attempt !!!", operationId);
        }

        if (Objects.equals(verificationCode, verification.getCode()) && Objects.equals(verification.getOperationId(), operationId)) {
            repository.transfer(cardDto.getCardFromNumber(), cardDto.getCardToNumber()
                    , cardDto.getAmount());
        } else {
            Logger.getLOG().addLogs("card from: " + cardDto.getCardFromNumber()
                    + " cardTo: " + cardDto.getCardToNumber()
                    + " amount: " + cardDto.getAmount().getValue() + " no commission " + "false");
            verificationCode = String.valueOf(new Random().nextInt(100000, 999999));
            throw new TransferException("Code error", operationId);
        }

        Logger.getLOG().addLogs("card from: " + cardDto.getCardFromNumber()
                + " cardTo: " + cardDto.getCardToNumber()
                + " amount: " + cardDto.getAmount().getValue() + " no commission " + "true");
        verificationCode = String.valueOf(new Random().nextInt(100000, 999999));
        return new ResponsesOperationIdTransfer(String.valueOf(operationId));
    }

    public ResponsesOperationIdTransfer transfer(CardDto cardDto) {
        verificationCode = String.valueOf(new Random().nextInt(100000, 999999));
        System.out.println(verificationCode);
        this.cardDto = cardDto;
        this.operationId = new OperationIdTransfer().getId();
        if (repository.getCards().containsKey(cardDto.getCardFromNumber()) && repository.getCards().containsKey(cardDto.getCardToNumber())
                && repository.getCards().get(cardDto.getCardFromNumber()) != repository.getCards().get(cardDto.getCardToNumber())) {
            CardUser cardFromRepository = repository.getCards().get(cardDto.getCardFromNumber());
            CardUser cardFromTransfer = parseCardTransferFrom(cardDto);
            if (cardFromTransfer.getCvv().equals(cardFromRepository.getCvv()) && cardFromTransfer.getMm().equals(cardFromRepository.getMm()) &&
                    cardFromTransfer.getGg().equals(cardFromRepository.getGg()) && (!(cardFromTransfer.getAmount().getValue() < 1) &&
                    cardFromTransfer.getAmount().getValue() <= cardFromRepository.getAmount().getValue())) {
            } else {
                Logger.getLOG().addLogs("card from: " + cardDto.getCardFromNumber()
                        + " cardTo: " + cardDto.getCardToNumber()
                        + " amount: " + cardDto.getAmount().getValue() + " no commission " + "false");
                throw new TransferException("No card valid", operationId);
            }
        } else {
            Logger.getLOG().addLogs("card from: " + cardDto.getCardFromNumber()
                    + " cardTo: " + cardDto.getCardToNumber()
                    + " amount: " + cardDto.getAmount().getValue() + " no commission " + "false");
            throw new TransferException("No card", operationId);
        }
        return new ResponsesOperationIdTransfer(operationId);
    }

    private CardUser parseCardTransferFrom(CardDto transfer) {
        String[] str = transfer.getCardFromValidTill().split("/");
        return new CardUser(transfer.getCardFromNumber(), transfer.getCardFromCVV(), str[0], str[1], transfer.getAmount());
    }
}
