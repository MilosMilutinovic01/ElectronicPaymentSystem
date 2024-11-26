package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.exception.CardAuthenticationException;
import org.bankexample.bankbackend.exception.CardNumberDoesNotExistException;
import org.bankexample.bankbackend.model.Card;
import org.bankexample.bankbackend.repository.CardRepository;
import org.bankexample.bankbackend.service.CardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    @Value("${BANK_PAN_PREFIX:100090}")
    private String firstPanNumbers;

    private final CardRepository cardRepository;

    @Override
    public void authenticateCard(String cardNumber, int expirationMonth, int expirationYear, String cvv) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new CardNumberDoesNotExistException(cardNumber));

        if (!(card.getExpirationMonth() == expirationMonth
                && card.getExpirationYear() == expirationYear
                && card.getCvv().equals(cvv))) {
           throw new CardAuthenticationException();
        }

        YearMonth yearMonth = YearMonth.of(expirationYear, expirationMonth);
        int lastDayOfMonth = yearMonth.lengthOfMonth();
        LocalDateTime lastDayDateTime = LocalDateTime.of(expirationYear, expirationMonth, lastDayOfMonth, 23, 59, 59);
        if (lastDayDateTime.isBefore(LocalDateTime.now())) {    // Expired
            throw new CardAuthenticationException();
        }

    }

    @Override
    public boolean clientInSameBank(String cardNumber) {
        if (!cardNumber.startsWith(firstPanNumbers)) {
            return false;
        }
        return cardRepository.findByCardNumber(cardNumber).isPresent();
    }

    @Override
    public String getBankAccountNumber(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new CardNumberDoesNotExistException(cardNumber));
        return card.getAccountNumber();
    }

}
