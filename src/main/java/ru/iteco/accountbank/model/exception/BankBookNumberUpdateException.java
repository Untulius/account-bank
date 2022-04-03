package ru.iteco.accountbank.model.exception;

public class BankBookNumberUpdateException extends RuntimeException {
    public BankBookNumberUpdateException() {
    }

    public BankBookNumberUpdateException(String message) {
        super(message);
    }
}
