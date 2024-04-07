package com.dev.rest.restinvoice.exception;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException() {
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
