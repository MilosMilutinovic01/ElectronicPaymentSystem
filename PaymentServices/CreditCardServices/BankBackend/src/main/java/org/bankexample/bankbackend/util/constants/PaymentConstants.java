package org.bankexample.bankbackend.util.constants;

import org.springframework.beans.factory.annotation.Value;

public class PaymentConstants {

    // TODO https
    public static final String PAYMENT_URL = "http://localhost:8082/payment";
    public static final String PAYMENT_SUCCESS_URL = "http://localhost:8080/payment/success";
    public static final String PAYMENT_FAILED_URL = "http://localhost:8080/payment/failed";
    public static final String PAYMENT_ERROR_URL = "http://localhost:8080/payment/error";
    public static final String PCC_URL = "http://localhost:8083";

}
