package org.bankexample.bankbackend.model;

import lombok.Getter;

@Getter
public enum TransactionType {

    HOLD("HOLD");

    private final String typeName;

    TransactionType(String typeName) {
        this.typeName = typeName;
    }

}