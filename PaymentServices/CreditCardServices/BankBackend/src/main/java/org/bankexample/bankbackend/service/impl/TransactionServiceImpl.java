package org.bankexample.bankbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;
import org.bankexample.bankbackend.model.TransactionResult;
import org.bankexample.bankbackend.repository.TransactionRepository;
import org.bankexample.bankbackend.service.TransactionService;
import org.bankexample.bankbackend.service.CardValidationService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardValidationService cardValidationService;

    @Override
    // @Transactional TODO research
    public TransactionResultResponseDTO holdFunds(TransactionRequestDTO dto) {

        TransactionResultResponseDTO responseDTO = new TransactionResultResponseDTO();

        if (cardValidationService.validateCardAndFunds(dto.getCardNumber(), dto.getExpiryDate(), dto.getSecurityCode(), dto.getAmount())) {

            // Create transaction of type hold

            responseDTO.setTransactionResult(TransactionResult.SUCCESS);
        }

        return responseDTO;
    }
}
