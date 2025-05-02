package com.cirruslabs.store.exceptions;

public class PaymentException extends RuntimeException {
    public PaymentException() {
        super("Error creating a checkout session.");
    }
}
