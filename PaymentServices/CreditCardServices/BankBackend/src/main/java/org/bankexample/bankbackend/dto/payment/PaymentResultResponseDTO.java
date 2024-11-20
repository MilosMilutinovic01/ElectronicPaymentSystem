package org.bankexample.bankbackend.dto.payment;

import lombok.Data;
import org.bankexample.bankbackend.model.TransactionResult;

@Data
public class PaymentResultResponseDTO {

    private String paymentId;
    private TransactionResult transactionResult;
    private String merchantId;
    private String merchantOrderId;
    private String acquirerOrderId;
    private String acquirerTimestamp;
    private String issuerOrderId;
    private String issuerTimestamp;
    private String redirectUrl;
    private String message; // TODO
}
