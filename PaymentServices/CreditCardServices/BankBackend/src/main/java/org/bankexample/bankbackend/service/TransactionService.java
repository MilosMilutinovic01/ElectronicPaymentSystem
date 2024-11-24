package org.bankexample.bankbackend.service;

import org.bankexample.bankbackend.dto.transaction.TransactionRequestDTO;
import org.bankexample.bankbackend.dto.transaction.TransactionResultResponseDTO;

public interface TransactionService {

    TransactionResultResponseDTO holdFunds(TransactionRequestDTO dto);

}
