package com.gorecki.controller;

public class Operation {
    private final OperationType type;
    private final int operationValue;

    public Operation(OperationType operationType, int value) {
        type = operationType;
        operationValue = value;
    }

    public OperationType getOperationType() {
        return type;
    }

    public int getValue() {
        return operationValue;
    }
}
