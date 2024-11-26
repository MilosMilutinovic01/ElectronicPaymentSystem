package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.exception.BankAccountDoesNotExistException;
import org.bankexample.bankbackend.exception.CardAuthenticationException;
import org.bankexample.bankbackend.exception.CardNumberDoesNotExistException;
import org.bankexample.bankbackend.exception.InsufficientFundsInBankAccountException;
import org.bankexample.bankbackend.mapper.TransactionMapper;
import org.bankexample.bankbackend.model.Transaction;
import org.bankexample.bankbackend.model.TransactionResult;
import org.bankexample.bankbackend.model.TransactionType;
import org.bankexample.bankbackend.repository.TransactionRepository;
import org.bankexample.bankbackend.service.BankAccountService;
import org.bankexample.bankbackend.service.TransactionService;
import org.bankexample.bankbackend.service.CardService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    private final CardService cardService;
    private final TransactionMapper transactionMapper;

    @Override
    // @Transactional TODO research
    public TransactionResultResponseDTO holdFunds(TransactionRequestDTO dto) {

        Transaction transaction = transactionMapper.mapTransactionRequestDTOToTransaction(dto);
        transaction.setTransactionType(TransactionType.HOLD);
        String bankAccountNumber = cardService.getBankAccountNumber(dto.getCardNumber());
        transaction.setSenderBankAccountNumber(bankAccountNumber);
        transaction = transactionRepository.save(transaction);

        transaction.setIssuerOrderId(transaction.getId().toString());
        transaction.setIssuerTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toString());

        String message = "";
        try {
            cardService.authenticateCard(dto.getCardNumber(), dto.getExpirationMonth(), dto.getExpirationYear(), dto.getSecurityCode());
            bankAccountService.checkAvailableFunds(dto.getAmount(), bankAccountNumber);
            transaction.setTransactionResult(TransactionResult.SUCCESS);
        } catch (CardNumberDoesNotExistException | CardAuthenticationException
                | BankAccountDoesNotExistException | InsufficientFundsInBankAccountException e) {
            transaction.setTransactionResult(TransactionResult.FAILURE);
            message = e.getMessage();
        } catch (Exception exception) {
            transaction.setTransactionResult(TransactionResult.ERROR);
            message = exception.getMessage();
        }

        transactionRepository.save(transaction);
        return transactionMapper.mapToTransactionResultResponseDTO(transaction, message);
    }
}
