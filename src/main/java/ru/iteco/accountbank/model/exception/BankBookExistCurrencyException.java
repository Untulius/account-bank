package ru.iteco.accountbank.model.exception;

public class BankBookExistCurrencyException extends RuntimeException {
    public BankBookExistCurrencyException() {
    }

    public BankBookExistCurrencyException(String message) {
        super(message);
    }
}
