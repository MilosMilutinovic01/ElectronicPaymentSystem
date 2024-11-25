package org.pccexample.pccbackend.dto.transaction;

import lombok.Data;
import org.pccexample.pccbackend.model.TransactionResult;

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
