package org.bankexample.bankbackend.dto.transaction;

import lombok.Data;
import org.bankexample.bankbackend.model.TransactionResult;

@Data
public class TransactionResultResponseDTO {

    private TransactionResult transactionResult;
    private String merchantId;
    private String merchantOrderId;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;

}
