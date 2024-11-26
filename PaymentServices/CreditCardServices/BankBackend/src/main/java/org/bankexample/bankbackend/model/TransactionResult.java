package org.bankexample.bankbackend.model;

import lombok.Getter;

@Getter
public enum TransactionResult {

    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    ERROR("ERROR");


    private final String resultName;

    TransactionResult(String resultName) {
        this.resultName = resultName;
    }

}