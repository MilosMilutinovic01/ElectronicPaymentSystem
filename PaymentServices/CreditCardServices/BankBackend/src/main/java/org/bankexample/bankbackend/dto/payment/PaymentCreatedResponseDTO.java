package org.bankexample.bankbackend.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCreatedResponseDTO {

    private String paymentId;
    private String paymentUrl;
    private String merchantId;
    private String merchantOrderId;
    private BigDecimal amount;
//    private String successUrl;
//    private String failedUrl;
//    private String errorUrl;

}
