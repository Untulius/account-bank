package ru.iteco.accountbank.model.exception;

public class BankBookNotFoundException extends RuntimeException {

    public BankBookNotFoundException() {
    }

    public BankBookNotFoundException(String message) {
        super(message);
    }

}
