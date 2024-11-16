package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.exception.BankAccountDoesNotExistException;
import org.bankexample.bankbackend.exception.CardAuthenticationException;
import org.bankexample.bankbackend.exception.CardNumberDoesNotExistException;
import org.bankexample.bankbackend.exception.InsufficientFundsInBankAccountException;
import org.bankexample.bankbackend.model.Transaction;
import org.bankexample.bankbackend.model.TransactionResult;
import org.bankexample.bankbackend.repository.TransactionRepository;
import org.bankexample.bankbackend.service.BankAccountService;
import org.bankexample.bankbackend.service.TransactionService;
import org.bankexample.bankbackend.service.CardService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    private final CardService cardService;

    @Override
    // @Transactional TODO research
    public TransactionResultResponseDTO holdFunds(TransactionRequestDTO dto) {

        Transaction transaction = new Transaction();
        try {
            cardService.authenticateCard(dto.getCardNumber(), dto.getExpirationMonth(), dto.getExpirationYear(), dto.getSecurityCode());
            String bankAccountNumber = cardService.getBankAccountNumber(dto.getCardNumber());
            bankAccountService.checkAvailableFunds(dto.getAmount(), bankAccountNumber);
            transaction.setTransactionResult(TransactionResult.SUCCESS);
        } catch (CardNumberDoesNotExistException | CardAuthenticationException
                | BankAccountDoesNotExistException | InsufficientFundsInBankAccountException e) {
            transaction.setTransactionResult(TransactionResult.FAILURE);
        } catch (Exception exception) {
            transaction.setTransactionResult(TransactionResult.ERROR);
        }
        transactionRepository.save(transaction);
        TransactionResultResponseDTO responseDTO = new TransactionResultResponseDTO(); // TODO mapper
        return responseDTO;
    }
}
